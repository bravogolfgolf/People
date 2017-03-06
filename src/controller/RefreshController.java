package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.RefreshResponder;
import responder.View;

public class RefreshController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final RefreshResponder responder;
    private final View view;

    public RefreshController(Request request, UseCase useCase, RefreshResponder responder, View view) {
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
