package sc.fiji.ij_linking.ops.example;

import sc.fiji.ij_linking.ops.AbstractOperationQueryObject;

import java.util.Map;

public class BDVOperationQueryObject extends AbstractOperationQueryObject {
    String dataset;
    String timepoint;

    public static BDVOperationQueryObject fromMap(Map<String, String> query) throws Exception {
        return mapToObject(query, BDVOperationQueryObject.class);
    }

    public Map<String,String> toMap() throws Exception {
        return objectToMap(this);
    }

    @Override
    public String toString() {
        return "BDVOperationQueryObject{" +
                "dataset='" + dataset + '\'' +
                ", timepoint='" + timepoint + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception {
        Map<String,String> query = Map.of("dataset", "test", "timepoint", "0");
        BDVOperationQueryObject bdvQuery = BDVOperationQueryObject.fromMap(query);
        System.out.println(bdvQuery);
        Map<String, String> Map2 = bdvQuery.toMap();
        for ( Map.Entry<String, String> entry : Map2.entrySet() ) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }


    }
}
