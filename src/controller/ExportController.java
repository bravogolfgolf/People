package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.ExportResponder;
import responder.View;

public class ExportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final ExportResponder responder;
    private final View view;

    public ExportController(Request request, UseCase useCase, ExportResponder responder, View view) {
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
