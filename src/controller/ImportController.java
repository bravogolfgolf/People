package controller;

import builderfactory.*;
import responder.ImportResponder;
import responder.View;

import java.util.Map;

public class ImportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final ImportResponder responder;
    private final View view;

    public ImportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, ImportResponder responder, View view) {
        this.request = request.make("Import", args);
        this.useCase = useCase.make("Import", responder);
        this.responder = responder;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return view.generateView(responder.getViewModel());
    }
}
