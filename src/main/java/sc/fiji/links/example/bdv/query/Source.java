package sc.fiji.links.example.bdv.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import sc.fiji.links.example.bdv.serialization.SerializableClass;

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
