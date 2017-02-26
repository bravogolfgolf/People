package usecase.addperson;

import databasegateway.AddPersonGateway;
import requestor.InputBoundary;
import requestor.Request;
import responder.Presenter;

public class AddPersonUseCase implements InputBoundary {
    private final AddPersonGateway repository;

    public AddPersonUseCase(AddPersonGateway repository, Presenter presenter) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request) {
        AddPersonRequest r = (AddPersonRequest) request;
        repository.addPerson(r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
    }

}
