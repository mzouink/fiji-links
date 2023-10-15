package sc.fiji.links.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.imglib2.type.numeric.ARGBType;

import java.io.IOException;

public class ARGBTypeSerializer extends JsonSerializer<ARGBType> {
    @Override
    public void serialize(ARGBType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.get());
    }
}

