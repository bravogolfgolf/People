package main;

import ui.Controller;

import java.util.Map;

interface ControllerFactory {
    Controller make(String controller, Map<Integer, Object> args);
}
