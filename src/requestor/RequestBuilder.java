package requestor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class RequestBuilder {
    private final Map<String, Class<?>> requests;

    public RequestBuilder(Map<String, Class<?>> requests) {
        this.requests = requests;
    }

    public Request make(String request, Map<Integer, Object> args) {
        Class<?> aClass = requests.get(request);
        Field[] fields = aClass.getFields();
        Object object = null;

        try {
            object = aClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        int i = 0;
        for (Field field : fields) {
            try {
                field.set(object, args.get(i++));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (Request) object;
    }
}
