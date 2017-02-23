package domain.refresh;

import domain.*;

import java.util.HashMap;
import java.util.Map;

public class RefreshUseCase implements InputBoundary {

    private final RefreshGateway repository;
    private final Presenter presenter;
    private final ResponseBuilder builder;

    public RefreshUseCase(RefreshGateway repository, ResponseBuilder builder, Presenter presenter) {
        this.repository = repository;
        this.builder = builder;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        Map<Integer, Person> people = repository.getPeople();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, people);
        Response response = builder.make("RefreshResponse", args);
        presenter.present(response);
    }
}
