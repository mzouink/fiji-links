package sc.fiji.ij_linking.ops;

import net.imagej.ImageJPlugin;

import java.util.Map;

public interface URLOperation extends ImageJPlugin {
    void run(String path, Map<String, String> query);
}
