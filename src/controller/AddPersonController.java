package controller;

import builderfactory.*;
import responder.AddPersonResponder;
import responder.View;

import java.util.Map;

public class AddPersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final AddPersonResponder responder;
    private final View view;

    public AddPersonController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, AddPersonResponder responder, View view) {
        this.request = request.make("AddPerson", args);
        this.useCase = useCase.make("AddPerson", responder);
        this.responder = responder;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return view.generateView(responder.getViewModel());
    }
}
