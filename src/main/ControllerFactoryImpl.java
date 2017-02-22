package main;

import ui.*;

import java.util.Map;

public class ControllerFactoryImpl implements ControllerFactory {

    private final RequestBuilder builder;
    private final UseCaseFactory factory;
    private final PersonTablePanelPresenter presenter;

    ControllerFactoryImpl(RequestBuilder builder, UseCaseFactory factory, PersonTablePanelPresenter presenter) {
        this.builder = builder;
        this.factory = factory;
        this.presenter = presenter;
    }

    @Override
    public Controller make(String controller, Map<Integer, Object> args, View view) {
        if (controller.equals("RefreshController"))
            return new RefreshController(builder, args, factory, presenter, view);
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
