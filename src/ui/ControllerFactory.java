package ui;

import domain.Presenter;
import ui.contoller.Controller;

import java.util.Map;

public interface ControllerFactory {
    Controller make(String controller, Map<Integer, Object> args, Presenter presenter, View view);
}
