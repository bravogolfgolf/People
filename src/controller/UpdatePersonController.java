package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.Responder;

public class UpdatePersonController extends Controller {
    private final Request request;
    private final UseCase usecase;
    private final Responder responder;

    public UpdatePersonController(Request request, UseCase usecase, Responder responder) {
        this.request = request;
        this.usecase = usecase;
        this.responder = responder;
    }

    @Override
    public Object execute() {
        usecase.execute(request);
        return responder.generateView();
    }
}
