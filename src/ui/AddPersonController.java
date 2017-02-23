package ui;

import domain.InputBoundary;
import domain.Request;

import java.util.Map;

public class AddPersonController implements Controller {
    private final Request request;
    private final InputBoundary useCase;
    private final PersonTablePanelPresenter presenter;
    private final View view;

    public AddPersonController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, PersonTablePanelPresenter presenter, View view) {
        this.request = request.make("AddPersonRequest", args);
        this.useCase = useCase.make("AddPersonUseCase");
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public void execute() {
        useCase.execute(request);
        view.update(presenter.getViewModel());
    }
}
