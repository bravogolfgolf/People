package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import responder.AddPersonResponder;

public class AddPersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final AddPersonResponder responder;

    public AddPersonController(Request request, UseCase useCase, AddPersonResponder responder) {
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
