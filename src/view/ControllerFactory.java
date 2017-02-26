package view;

import other.Controller;
import responder.Presenter;

import java.util.Map;

public interface ControllerFactory {
    Controller make(String controller, Map<Integer, Object> args, Presenter presenter, View view);
}
