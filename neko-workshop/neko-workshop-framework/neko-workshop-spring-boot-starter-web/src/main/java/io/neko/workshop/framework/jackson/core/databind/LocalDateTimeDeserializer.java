package io.neko.workshop.framework.jackson.core.databind;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * LocalDateTime反序列化规则
 * <p>
 * 会将毫秒级时间戳反序列化为LocalDateTime
 */
public class LocalDateTimeDeserializer extends ValueDeserializer<LocalDateTime> {

    public static final LocalDateTimeDeserializer INSTANCE = new LocalDateTimeDeserializer();

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(p.getValueAsLong()), ZoneId.systemDefault());
    }
}
