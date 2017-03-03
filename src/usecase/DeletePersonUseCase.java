package usecase;

import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import presenter.Presenter;

public class DeletePersonUseCase extends UseCase {
    private final PersonRepository repository;

    public DeletePersonUseCase(PersonRepository repository, Presenter presenter) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request) {
        DeletePersonRequest r = (DeletePersonRequest) request;
        repository.deletePerson(r.id);
    }
}
