package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.DeletePersonResponder;
import responder.View;

public class DeletePersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final DeletePersonResponder responder;
    private final View view;

    public DeletePersonController(Request request, UseCase useCase, DeletePersonResponder responder, View view) {
        this.request = request;
        this.useCase = useCase;
        this.responder = responder;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return responder.generateView();
    }
}
