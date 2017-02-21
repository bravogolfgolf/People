package domain;

import java.util.Map;

public interface ResponseBuilder {
    Response make(String response, Map<Integer, Object> args);
}
