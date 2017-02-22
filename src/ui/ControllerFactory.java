package ui;

import java.util.Map;

public interface ControllerFactory {
    Controller make(String controller, Map<Integer, Object> args, View view);
}
