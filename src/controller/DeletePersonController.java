package controller;

import contollerfactory.Controller;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Presenter;
import view.View;

import java.util.ArrayList;
import java.util.Map;

public class DeletePersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final Presenter presenter;
    private final View view;

    public DeletePersonController(RequestBuilder builder, Map<Integer, Object> args, UseCaseFactory factory, Presenter presenter, View view) {
        this.request = builder.make("DeletePerson", args);
        this.useCase = factory.make("DeletePerson", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return new ArrayList<>();
    }
}
