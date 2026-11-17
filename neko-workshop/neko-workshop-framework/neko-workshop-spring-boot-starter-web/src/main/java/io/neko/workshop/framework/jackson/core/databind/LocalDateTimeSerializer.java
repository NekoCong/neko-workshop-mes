package io.neko.workshop.framework.jackson.core.databind;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * LocalDateTime序列化规则
 * <p>
 * 会将LocalDateTime序列化为毫秒级时间戳
 */
public class LocalDateTimeSerializer extends ValueSerializer<LocalDateTime> {

    public static final LocalDateTimeSerializer INSTANCE = new LocalDateTimeSerializer();

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializationContext serializers) {
        gen.writeNumber(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
