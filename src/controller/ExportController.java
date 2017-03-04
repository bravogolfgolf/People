package controller;

import builderfactory.*;
import responder.ExportResponder;
import responder.View;

import java.util.Map;

public class ExportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final ExportResponder responder;
    private final View view;

    public ExportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, ExportResponder responder, View view) {
        this.request = request.make("Export", args);
        this.useCase = useCase.make("Export", responder);
        this.responder = responder;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return view.generateView(responder.getViewModel());
    }
}
