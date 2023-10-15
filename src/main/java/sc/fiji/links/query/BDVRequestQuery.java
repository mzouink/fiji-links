package sc.fiji.links.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.imglib2.realtransform.AffineTransform3D;
import net.imglib2.type.numeric.ARGBType;
import sc.fiji.links.serialization.SerializableClass;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BDVRequestQuery extends SerializableClass {

    public class Source extends SerializableClass {
        @JsonProperty("data")
        public Data data;

        @JsonProperty("viewer_state")
        public ViewerState viewerState;

        @Override
        public String toString() {
            return "data=[" + data + "] state=" + viewerState;
        }
    }

    public class Data extends SerializableClass {
        @JsonProperty("link")
        public String link;

        @JsonProperty("dataset")
        public String dataset;

        @Override
        public String toString() {
            return "link=" + link + " dataset=" + dataset;
        }
    }

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

    @JsonProperty("sources")
    public List<Source> sources;

    @JsonProperty("viewer_transformation")
    public AffineTransform3D viewerTransformation;

    @Override
    public String toString() {
        return "BDVRequest: sources=" + sources + " viewerTransformation=" + viewerTransformation;
    }

    public static void main(String[] args) throws Exception {
        BDVRequestQuery request = new BDVRequestQuery();
        BDVRequestQuery.ViewerState viewerState = request.new ViewerState();
        viewerState.color = new ARGBType(0x00ff00);
        viewerState.range = List.of(0, 255);
//        viewerState.transformation = new AffineTransform3D();

        Source source = request.new Source();
        source.data = request.new Data();
        source.data.dataset = "dataset_test";
        source.data.link = "path_test";
        source.viewerState = viewerState;

        BDVRequestQuery bdvRequestQuery = new BDVRequestQuery();
        bdvRequestQuery.sources = List.of(source);
        bdvRequestQuery.viewerTransformation = new AffineTransform3D();

        System.out.println(bdvRequestQuery);

        String serializedData = bdvRequestQuery.serialize();
        System.out.println("Serialized: " + serializedData);

        BDVRequestQuery deserializedObject = BDVRequestQuery.deserialize(serializedData, BDVRequestQuery.class);
        System.out.println("Deserialized attribute: " + deserializedObject);

        String encoded = URLEncoder.encode(serializedData, StandardCharsets.UTF_8);
        System.out.println(encoded);
        String decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        System.out.println(decoded);
    }


}
