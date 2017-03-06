package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.ImportResponder;
import responder.View;

public class ImportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final ImportResponder responder;
    private final View view;

    public ImportController(Request request, UseCase useCase, ImportResponder responder, View view) {
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
