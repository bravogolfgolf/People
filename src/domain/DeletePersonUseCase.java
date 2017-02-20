package domain;

public class DeletePersonUseCase implements UseCase {
    private final RepositoryInteractor repository;
    private final PresenterInteractor presenter;

    public DeletePersonUseCase(RepositoryInteractor repository, PresenterInteractor presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        DeletePersonRequest r = (DeletePersonRequest) request;
        repository.deletePerson(r.id);
        presenter.presentPeople(repository.getPeople());
    }
}
