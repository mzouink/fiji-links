package sc.fiji.links.linux;

import net.imagej.ImageJPlugin;

import java.util.Map;

public interface URLOperation extends ImageJPlugin {
    void run(String path, Map<String, String> query);
}