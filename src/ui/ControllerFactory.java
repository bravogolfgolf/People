package ui;

import ui.Controller;

import java.util.Map;

public interface ControllerFactory {
    Controller make(String controller, Map<Integer, Object> args);
}
