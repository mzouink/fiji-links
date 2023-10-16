package sc.fiji.links.example.bdv.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import net.imglib2.type.numeric.ARGBType;

import java.io.IOException;

public class ARGBTypeDeserializer extends JsonDeserializer<ARGBType> {
    @Override
    public ARGBType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return new ARGBType(p.getIntValue());
    }
}