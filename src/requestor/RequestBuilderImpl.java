package requestor;

import java.lang.reflect.Field;
import java.util.Map;

public class RequestBuilderImpl {
    private final Map<String, Class<? extends Request>> requests;

    public RequestBuilderImpl(Map<String, Class<? extends Request>> requests) {
        this.requests = requests;
    }

    public Request get(String request, Map<Integer, Object> args) {
        Class aClass = requests.get(request);
        Field[] fields = aClass.getFields();
        Object object = null;
        try {
            object = aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
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
