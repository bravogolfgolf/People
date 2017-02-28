package usecase.updateperson;

import databasegateway.PersonRepository;
import requestor.Request;
import requestor.UseCase;

public class UpdatePersonUseCase extends UseCase {
    private final PersonRepository repository;

    public UpdatePersonUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request) {
        UpdatePersonRequest r = (UpdatePersonRequest) request;
        repository.updatePerson(r.id, r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
    }
}
