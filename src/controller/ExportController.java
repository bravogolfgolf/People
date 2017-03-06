package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.ExportResponder;

public class ExportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final ExportResponder responder;

    public ExportController(Request request, UseCase useCase, ExportResponder responder) {
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
