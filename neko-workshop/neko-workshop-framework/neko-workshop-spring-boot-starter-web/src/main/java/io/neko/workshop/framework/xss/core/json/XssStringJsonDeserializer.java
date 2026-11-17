package io.neko.workshop.framework.xss.core.json;

import io.neko.workshop.framework.xss.core.clean.XssCleaner;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.jdk.StringDeserializer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * XSS 过滤 jackson 反序列化器。
 * 在反序列化的过程中，会对字符串进行 XSS 过滤。
 *
 * @author Hccake
 */
@Slf4j
@AllArgsConstructor
public class XssStringJsonDeserializer extends StringDeserializer {

    private final XssCleaner xssCleaner;

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) {
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            return xssCleaner.clean(p.getText());
        }
        JsonToken t = p.currentToken();
        // [databind#381]
        if (t == JsonToken.START_ARRAY) {
            return _deserializeFromArray(p, ctxt);
        }
        // need to gracefully handle byte[] data, as base64
        if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object ob = p.getEmbeddedObject();
            if (ob == null) {
                return null;
            }
            if (ob instanceof byte[]) {
                return ctxt.getBase64Variant().encode((byte[]) ob, false);
            }
            // otherwise, try conversion using toString()...
            return ob.toString();
        }
        // 29-Jun-2020, tatu: New! "Scalar from Object" (mostly for XML)
        if (t == JsonToken.START_OBJECT) {
            return ctxt.extractScalarFromObject(p, this, String.class);
        }

        if (t.isScalarValue()) {
            String text = p.getValueAsString();
            return xssCleaner.clean(text);
        }
        return (String) ctxt.handleUnexpectedToken(String.class, p);
    }
}
