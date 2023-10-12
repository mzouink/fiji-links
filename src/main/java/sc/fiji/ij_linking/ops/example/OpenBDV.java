package sc.fiji.ij_linking.ops.example;

import org.scijava.plugin.Plugin;
import sc.fiji.ij_linking.ops.URLOperation;

import java.util.Map;

@Plugin(type = URLOperation.class, name = "OpenBDV")
public class OpenBDV implements URLOperation {
    @Override
    public void run(String path, Map<String, String> query) {
        System.out.println("OpenBDV: " + path);
        for ( Map.Entry<String, String> entry : query.entrySet() ) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
    }
}
