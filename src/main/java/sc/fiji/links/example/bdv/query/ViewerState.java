package sc.fiji.links.example.bdv.query;


import com.fasterxml.jackson.annotation.JsonProperty;
import net.imglib2.type.numeric.ARGBType;
import sc.fiji.links.example.bdv.serialization.SerializableClass;

import java.util.List;

public class ViewerState extends SerializableClass {
    @JsonProperty("color")
    public ARGBType color;

//        @JsonProperty("transformation")
//        public AffineTransform3D transformation;

    @JsonProperty("range")
    public List<Integer> range;

    @Override
    public String toString() {
        return " c=" + color
//                    + " transformation=" + transformation
                + " range=" + range;
    }
}

