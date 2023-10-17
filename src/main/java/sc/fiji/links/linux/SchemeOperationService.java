package sc.fiji.links.linux;

import net.imagej.ImageJService;
import org.scijava.InstantiableException;
import org.scijava.plugin.AbstractPTService;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.PluginInfo;
import org.scijava.service.Service;

import java.util.HashMap;

@Plugin(type = Service.class)
public class SchemeOperationService extends AbstractPTService<URLOperation> implements ImageJService {
    private static HashMap<String, PluginInfo<URLOperation>> ops = null;

    public static URLOperation getOperation(String name) throws InstantiableException {
        System.out.println("SchemeOperationService.getOperation: " + name);
        if (ops == null)
            new SchemeOperationService().initialize();
        if (!ops.containsKey(name))
            throw new InstantiableException("No operation found for name: " + name);
        return ops.get(name).createInstance();
    }
    @Override
    public void initialize() {
        System.out.println("SchemeOperationService.initialize");
        ops = new HashMap<>();
        for (final PluginInfo<URLOperation> info : getPlugins()) {
            String name = info.getName();
            System.out.println("SchemeOperationService.initialize: " + name);
            if (name == null || name.isEmpty()) {
                name = info.getClassName();
            }
            ops.put(name, info);
        }
    }

    @Override
    public Class<URLOperation> getPluginType() {
        return URLOperation.class;
    }
}