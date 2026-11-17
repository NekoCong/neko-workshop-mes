package io.neko.workshop.framework.jackson.core.databind;

import tools.jackson.databind.ext.javatime.deser.LocalTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalTimeSerializer;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static io.neko.workshop.framework.common.util.date.DateUtils.FORMAT_HOUR_MINUTE_SECOND;

public class LocalTimeJson {

    public static final LocalTimeSerializer SERIALIZER = new LocalTimeSerializer(DateTimeFormatter
            .ofPattern(FORMAT_HOUR_MINUTE_SECOND)
            .withZone(ZoneId.systemDefault()));

    public static final LocalTimeDeserializer DESERIALIZABLE = new LocalTimeDeserializer(DateTimeFormatter
            .ofPattern(FORMAT_HOUR_MINUTE_SECOND)
            .withZone(ZoneId.systemDefault()));

}
