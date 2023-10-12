package sc.fiji.ij_linking.ops;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class AbstractOperationQueryObject {

    protected static <T> T mapToObject(Map<String, String> map, Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // You might want to set accessibility if dealing with private fields

            if (map.containsKey(field.getName())) {
                field.set(obj, map.get(field.getName()));
            }
        }

        return obj;
    }

    protected static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true); // Make private fields accessible
            map.put(field.getName(), String.valueOf(field.get(obj)));
        }

        return map;
    }

    public static Map<String, String> getQueryMap(String query) {
        Map<String, String> map = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                try {
                    String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                    String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                    map.put(key, value);
                } catch (Exception e) {
                    // Handle exception - possibly related to incorrect encoding or a malformed parameter.
                }
            }
        }
        return map;
    }
}
