package sc.fiji.links.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.imglib2.realtransform.AffineTransform3D;

import java.io.IOException;

public class AffineTransform3DSerializer extends JsonSerializer<AffineTransform3D> {
    @Override
    public void serialize(AffineTransform3D value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        double[] values = new double[12];
        value.toArray(values);
        gen.writeArray(values, 0, values.length);
    }
}

