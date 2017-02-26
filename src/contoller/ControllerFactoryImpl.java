package contoller;


import other.Controller;
import requestor.RequestBuilder;
import responder.Presenter;
import usecase.UseCaseFactoryImpl;
import view.ControllerFactory;
import view.View;

import java.util.Map;

public class ControllerFactoryImpl implements ControllerFactory {
    private final RequestBuilder builder;
    private final UseCaseFactoryImpl factory;

    public ControllerFactoryImpl(RequestBuilder builder, UseCaseFactoryImpl factory) {
        this.builder = builder;
        this.factory = factory;
    }

    @Override
    public Controller make(String controller, Map<Integer, Object> args, Presenter presenter, View view) {
        if (controller.equals("RefreshController"))
            return new RefreshController(builder, args, factory, presenter, view);
        if (controller.equals("AddPersonController"))
            return new AddPersonController(builder, args, factory, presenter, view);
        if (controller.equals("DeletePersonController"))
            return new DeletePersonController(builder, args, factory, presenter, view);
        if (controller.equals("ExportController"))
            return new ExportController(builder, args, factory, presenter, view);
        if (controller.equals("ImportController"))
            return new ImportController(builder, args, factory, presenter, view);
        return null;
    }
}
