package io.neko.workshop.framework.desensitize.core.base.serializer;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import io.neko.workshop.framework.desensitize.core.base.annotation.DesensitizeBy;
import io.neko.workshop.framework.desensitize.core.base.handler.DesensitizationHandler;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.introspect.AnnotatedMember;
import tools.jackson.databind.ser.std.StdSerializer;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;

/**
 * 脱敏序列化器
 *
 * 实现 JSON 返回数据时，使用 {@link DesensitizationHandler} 对声明脱敏注解的字段，进行脱敏处理。
 *
 * @author gaibu
 */
@SuppressWarnings("rawtypes")
public class StringDesensitizeSerializer extends StdSerializer<String> {

    /**
     * 脱敏处理器。通过 {@link #createContextual(SerializationContext, BeanProperty)} 反射字段上的
     * {@link DesensitizeBy} 注解后设置，因此是 final，避免序列化时被并发修改。
     */
    @Getter
    private final DesensitizationHandler desensitizationHandler;

    /**
     * 当前被序列化的字段。Jackson 3 的 JsonGenerator 已移除 getOutputContext()/getCurrentValue()，
     * 因此改由 createContextual 阶段的 {@link BeanProperty} 直接保存字段信息。
     */
    @Getter
    private final Field field;

    protected StringDesensitizeSerializer() {
        this(null, null);
    }

    protected StringDesensitizeSerializer(DesensitizationHandler desensitizationHandler, Field field) {
        super(String.class);
        this.desensitizationHandler = desensitizationHandler;
        this.field = field;
    }

    @Override
    public ValueSerializer<?> createContextual(SerializationContext serializerProvider, BeanProperty beanProperty) {
        if (beanProperty == null) {
            return this;
        }
        DesensitizeBy annotation = beanProperty.getAnnotation(DesensitizeBy.class);
        if (annotation == null) {
            return this;
        }
        return new StringDesensitizeSerializer(Singleton.get(annotation.handler()), resolveField(beanProperty));
    }

    /**
     * 解析属性对应的字段。
     *
     * <p>Jackson 3 在序列化由 Lombok {@code @Data} 生成 getter 的属性时，
     * {@link BeanProperty#getMember()} 返回的是 {@code AnnotatedMethod}（getter 对应的 {@code Method}），
     * 而不是字段；此时需按属性名 + 声明类查找真实字段，才能读取到字段上的脱敏注解。</p>
     */
    private static Field resolveField(BeanProperty beanProperty) {
        AnnotatedMember member = beanProperty.getMember();
        if (member == null) {
            return null;
        }
        Member javaMember = member.getMember();
        if (javaMember instanceof Field) {
            return (Field) javaMember;
        }
        Class<?> declaringClass = member.getDeclaringClass();
        String name = beanProperty.getName();
        if (declaringClass == null || StrUtil.isBlank(name)) {
            return null;
        }
        return ReflectUtil.getField(declaringClass, name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void serialize(String value, JsonGenerator gen, SerializationContext serializerProvider) {
        if (StrUtil.isBlank(value)) {
            gen.writeNull();
            return;
        }

        // 无法解析字段或处理器时，回退为输出原值，避免把正常字符串写成 null（数据丢失）
        if (this.field == null || this.desensitizationHandler == null) {
            gen.writeString(value);
            return;
        }

        // 自定义处理器：基于字段上的注解（字段已在 createContextual 阶段解析）
        DesensitizeBy[] annotations = AnnotationUtil.getCombinationAnnotations(field, DesensitizeBy.class);
        if (ArrayUtil.isEmpty(annotations)) {
            gen.writeString(value);
            return;
        }
        for (Annotation annotation : field.getAnnotations()) {
            if (AnnotationUtil.hasAnnotation(annotation.annotationType(), DesensitizeBy.class)) {
                value = this.desensitizationHandler.desensitize(value, annotation);
                gen.writeString(value);
                return;
            }
        }
        gen.writeString(value);
    }

}
