package ui;

import domain.InputBoundary;
import domain.Request;

import java.util.Map;

public class DeletePersonController implements Controller {
    private final Request request;
    private final InputBoundary useCase;
    private final PersonTablePanelPresenter presenter;
    private final View view;

    public DeletePersonController(RequestBuilder builder, Map<Integer, Object> args, UseCaseFactory factory, PersonTablePanelPresenter presenter, View view) {
        this.request = builder.make("DeletePersonRequest", args);
        this.useCase = factory.make("DeletePersonUseCase");
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public void execute() {
        useCase.execute(request);
        view.update(presenter.getViewModel());
    }
}
