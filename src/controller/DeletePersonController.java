package controller;

import builderfactory.*;
import responder.DeletePersonResponder;
import responder.View;

import java.util.Map;

public class DeletePersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final DeletePersonResponder responder;
    private final View view;

    public DeletePersonController(RequestBuilder builder, Map<Integer, Object> args, UseCaseFactory factory, DeletePersonResponder responder, View view) {
        this.request = builder.make("DeletePerson", args);
        this.useCase = factory.make("DeletePerson", responder);
        this.responder = responder;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return view.generateView(responder.getViewModel());
    }
}
