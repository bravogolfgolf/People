package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.RequestBuilder;
import builderfactory.UseCase;
import builderfactory.UseCaseFactory;
import presenter.Presenter;
import view.View;

import java.util.Map;

public class AddPersonController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final Presenter presenter;
    private final View view;

    public AddPersonController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
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
