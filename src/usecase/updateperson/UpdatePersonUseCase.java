package usecase.updateperson;

import database.Person;
import databasegateway.UpdatePersonGateway;
import entity.PersonTemplate;
import requestor.Request;
import requestor.UseCase;

public class UpdatePersonUseCase extends UseCase {
    private final UpdatePersonGateway repository;

    public UpdatePersonUseCase(UpdatePersonGateway repository) {
        this.repository = repository;
    }

    public void execute(Request request) {
        UpdatePersonRequest r = (UpdatePersonRequest) request;
        PersonTemplate person = new Person(r.id, r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
        repository.updatePerson(person);
    }
}
