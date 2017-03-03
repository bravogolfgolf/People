package usecase;

import gateway.PersonRepository;
import builderfactory.Request;
import builderfactory.UseCase;
import presenter.Presenter;

public class AddPersonUseCase extends UseCase {
    private final PersonRepository repository;

    public AddPersonUseCase(PersonRepository repository, Presenter presenter) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request) {
        AddPersonRequest r = (AddPersonRequest) request;
        repository.addPerson(r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
    }

}
