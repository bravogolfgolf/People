package contoller;

import other.Controller;
import other.View;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.Presenter;
import usecase.exportfile.ExportUseCase;

import java.util.Map;

public class ExportController implements Controller {
    private final Request request;
    private final ExportUseCase useCase;
    private final Presenter presenter;
    private final View view;

    public ExportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("ExportRequest", args);
        this.useCase = (ExportUseCase) useCase.make("ExportUseCase", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return 1;
    }
}
