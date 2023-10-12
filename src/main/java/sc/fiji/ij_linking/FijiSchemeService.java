package sc.fiji.ij_linking;

import org.scijava.InstantiableException;
import sc.fiji.ij_linking.ops.AbstractOperationQueryObject;
import sc.fiji.ij_linking.ops.SchemeOperationService;
import sc.fiji.ij_linking.ops.URLOperation;

import java.net.URI;
import java.util.Map;

public class FijiSchemeService {
    final URI uri;

    private FijiSchemeService(URI uri) {
        this.uri = uri;
    }

    public static FijiSchemeService process(URI uri) throws InstantiableException {
        if (uri == null || !uri.getScheme().equals("fiji")) {
            return null;
        }
        System.out.println("Detected Fiji URI scheme!");
        FijiSchemeService service = new FijiSchemeService(uri);
//        get the right operation
        URLOperation operation = SchemeOperationService.getOperation(uri.getAuthority());
        Map<String, String> queries = AbstractOperationQueryObject.getQueryMap(uri.getQuery());
        operation.run(uri.getPath(), queries);

        return service;
    }
}