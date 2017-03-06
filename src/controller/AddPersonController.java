package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.AddPersonResponder;
import responder.View;

public class AddPersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final AddPersonResponder responder;
    private final View view;

    public AddPersonController(Request request, UseCase useCase, AddPersonResponder responder, View view) {
        this.request = request;
        this.useCase = useCase;
        this.responder = responder;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return view.generateView(responder.getViewModel());
    }
}
