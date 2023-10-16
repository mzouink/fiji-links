package sc.fiji.links.example.bdv.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import sc.fiji.links.example.bdv.serialization.SerializableClass;

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