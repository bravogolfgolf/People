package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.Responder;

public class DeletePersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final Responder responder;

    public DeletePersonController(Request request, UseCase useCase, Responder responder) {
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
