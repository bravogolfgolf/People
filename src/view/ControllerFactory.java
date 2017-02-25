package view;

import respondor.Presenter;
import contoller.Controller;

import java.util.Map;

public interface ControllerFactory {
    Controller make(String controller, Map<Integer, Object> args, Presenter presenter, View view);
}
