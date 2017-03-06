package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.ImportResponder;

public class ImportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final ImportResponder responder;

    public ImportController(Request request, UseCase useCase, ImportResponder responder) {
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
