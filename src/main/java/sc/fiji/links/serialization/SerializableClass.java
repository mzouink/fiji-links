package sc.fiji.links.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.imglib2.realtransform.AffineTransform3D;
import net.imglib2.type.numeric.ARGBType;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SerializableClass {

    private static ObjectMapper OBJECT_MAPPER = null; // this will skip null values

    public static ObjectMapper getObjectMapper() {
        if (OBJECT_MAPPER == null) {
            OBJECT_MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
            SimpleModule module = new SimpleModule();
            module.addSerializer(ARGBType.class, new ARGBTypeSerializer());
            module.addDeserializer(ARGBType.class, new ARGBTypeDeserializer());
            module.addSerializer(AffineTransform3D.class, new AffineTransform3DSerializer());
            module.addDeserializer(AffineTransform3D.class, new AffineTransform3DDeserializer());
            OBJECT_MAPPER.registerModule(module);
        }
        return OBJECT_MAPPER;
    }

    public String serialize() throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(this);
    }

    public String toQuery() throws JsonProcessingException {
        return URLEncoder.encode(serialize(), StandardCharsets.UTF_8);
    }

    public static <T> T fromQuery(String query, Class<T> type) throws Exception {
        String decoded = URLDecoder.decode(query, StandardCharsets.UTF_8);
        return deserialize(decoded, type);
    }

    public static <T> T deserialize(String data, Class<T> type) throws Exception {
        return getObjectMapper().readValue(data, type);
    }
}
