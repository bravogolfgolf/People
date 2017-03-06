package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.DeletePersonResponder;

public class DeletePersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final DeletePersonResponder responder;

    public DeletePersonController(Request request, UseCase useCase, DeletePersonResponder responder) {
        this.request = request;
        this.useCase = useCase;
        this.responder = responder;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return responder.generateView();
    }
}
