package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.RefreshResponder;

public class RefreshController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final RefreshResponder responder;

    public RefreshController(Request request, UseCase useCase, RefreshResponder responder) {
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
