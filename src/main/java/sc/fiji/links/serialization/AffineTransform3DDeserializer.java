package sc.fiji.links.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import net.imglib2.realtransform.AffineTransform3D;

import java.io.IOException;


public class AffineTransform3DDeserializer extends JsonDeserializer<AffineTransform3D> {
    @Override
    public AffineTransform3D deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        double[] values = p.readValueAs(double[].class);
        AffineTransform3D transform = new AffineTransform3D();
        transform.set(values);
        return transform;
    }
}
