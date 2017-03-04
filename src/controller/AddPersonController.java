package controller;

import builderfactory.*;
import responder.AddPersonResponder;
import responder.RefreshResponder;
import responder.RefreshResponse;
import responder.View;

import java.util.Map;

public class AddPersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final RefreshResponder presenter;
    private final View view;

    public AddPersonController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, RefreshResponder presenter, View view) {
        this.request = request.make("AddPerson", args);
        this.useCase = useCase.make("AddPerson", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return "AddPersonUseCase";
    }
}
