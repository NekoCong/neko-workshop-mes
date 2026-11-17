package io.neko.workshop.framework.mybatis.core.type;

import io.neko.workshop.framework.common.util.json.JsonUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import tools.jackson.core.type.TypeReference;

import java.util.Set;

/**
 * 参考 {@link com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler} 实现
 * 在我们将字符串反序列化为 Set 并且泛型为 Long 时，如果每个元素的数值太小，会被处理成 Integer 类型，导致可能存在隐性的 BUG。
 *
 * 例如说哦，SysUserDO 的 postIds 属性
 *
 * @author 灵喵工坊
 */
public class JsonLongSetTypeHandler extends AbstractJsonTypeHandler<Set<Long>> {

    private static final TypeReference<Set<Long>> TYPE_REFERENCE = new TypeReference<Set<Long>>(){};

    public JsonLongSetTypeHandler() {
        super(Set.class);
    }

    /**
     * MyBatis 在根据 {@link com.baomidou.mybatisplus.annotation.TableField#typeHandler()} 实例化处理器时，
     * 会优先使用 {@code (Class)} 构造器（见 {@code org.apache.ibatis.type.TypeHandlerRegistry#getInstance}）。
     * 缺少该构造器会抛出 {@code NoSuchMethodException: <init>(java.lang.Class)}。
     */
    public JsonLongSetTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    public Set<Long> parse(String json) {
        return JsonUtils.parseObject(json, TYPE_REFERENCE);
    }

    @Override
    public String toJson(Set<Long> obj) {
        return JsonUtils.toJsonString(obj);
    }

}
