package contoller;

import other.Controller;
import other.View;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.Presenter;
import usecase.importfile.ImportUseCase;

import java.util.Map;

public class ImportController implements Controller {
    private final Request request;
    private final ImportUseCase useCase;
    private final Presenter presenter;
    private final View view;

    public ImportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("ImportRequest", args);
        this.useCase = (ImportUseCase) useCase.make("ImportUseCase", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return null;
    }
}
