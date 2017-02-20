package domain;

public class RefreshUseCase implements UseCase {
    private final RefreshGateway repository;
    private final Presenter presenter;

    public RefreshUseCase(RefreshGateway repository, Presenter presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        presenter.present(repository.getPeople());
    }
}
