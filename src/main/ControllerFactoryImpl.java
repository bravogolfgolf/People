package main;

import ui.*;

import java.util.Map;

public class ControllerFactoryImpl implements ControllerFactory {

    private final RequestBuilder builder;
    private final UseCaseFactory factory;

    ControllerFactoryImpl(RequestBuilder builder, UseCaseFactory factory) {

        this.builder = builder;
        this.factory = factory;
    }

    @Override
    public Controller make(String controller, Map<Integer, Object> args) {
        if (controller.equals("RefreshController"))
            return new RefreshController(builder, args, factory);
        if (controller.equals("AddPersonController"))
            return new AddPersonController(builder, args, factory);
        if (controller.equals("DeletePersonController"))
            return new DeletePersonController(builder, args, factory);
        if (controller.equals("ExportController"))
            return new ExportController(builder, args, factory);
        if (controller.equals("ImportController"))
            return new ImportController(builder, args, factory);
        return null;
    }
}
