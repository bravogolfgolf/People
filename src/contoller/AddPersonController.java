package contoller;

import other.Controller;
import other.View;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.Presenter;
import usecase.addperson.AddPersonUseCase;

import java.util.Map;

public class AddPersonController implements Controller {
    private final Request request;
    private final AddPersonUseCase useCase;
    private final Presenter presenter;
    private final View view;

    public AddPersonController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("AddPersonRequest", args);
        this.useCase = (AddPersonUseCase) useCase.make("AddPersonUseCase", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return "AddPersonUseCase";
    }
}
