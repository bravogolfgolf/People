package contoller;

import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Controller;
import responder.Presenter;
import responder.View;

import java.util.ArrayList;
import java.util.Map;

public class DeletePersonController implements Controller {
    private final Request request;
    private final UseCase useCase;
    private final Presenter presenter;
    private final View view;

    public DeletePersonController(RequestBuilder builder, Map<Integer, Object> args, UseCaseFactory factory, Presenter presenter, View view) {
        this.request = builder.make("DeletePersonRequest", args);
        this.useCase = factory.make("DeletePersonUseCase");
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return new ArrayList<>();
    }
}
