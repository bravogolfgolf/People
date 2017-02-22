package ui;

import domain.InputBoundary;
import domain.Presenter;
import domain.Request;

import java.util.Map;

public class RefreshController implements Controller {
    private final Request request;
    private final InputBoundary useCase;
    private final Presenter presenter;
    private final View view;

    public RefreshController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.presenter = presenter;
        this.view = view;
        this.request = request.make("RefreshRequest", args);
        this.useCase = useCase.make("RefreshUseCase");
    }

    @Override
    public void execute() {
        useCase.execute(request);
        view.update(presenter.getViewModel());
    }
}
