package controller;

import builderfactory.*;
import responder.RefreshResponder;
import responder.View;

import java.util.Map;

public class RefreshController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final RefreshResponder responder;
    private final View view;

    public RefreshController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, RefreshResponder responder, View view) {
        this.request = request.make("Refresh", args);
        this.useCase = useCase.make("Refresh", responder);
        this.responder = responder;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return view.generateView(responder.getViewModel());
    }
}
