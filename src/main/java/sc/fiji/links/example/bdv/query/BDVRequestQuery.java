package sc.fiji.links.example.bdv.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.imglib2.realtransform.AffineTransform3D;
import sc.fiji.links.example.bdv.serialization.SerializableClass;

import java.util.List;

public class BDVRequestQuery extends SerializableClass {

    @JsonProperty("sources")
    public List<Source> sources;

    @JsonProperty("viewer_transformation")
    public AffineTransform3D viewerTransformation;

    @Override
    public String toString() {
        return "BDVRequest: sources=" + sources + " viewerTransformation=" + viewerTransformation;
    }
}

