package usecase.addperson;

import databasegateway.AddPersonGateway;
import requestor.InputBoundary;
import requestor.Request;

public class AddPersonUseCase implements InputBoundary {
    private final AddPersonGateway repository;
    private final InputBoundary refreshUseCase;

    public AddPersonUseCase(AddPersonGateway repository, InputBoundary refreshUseCase) {
        this.repository = repository;
        this.refreshUseCase = refreshUseCase;
    }

    @Override
    public void execute(Request request) {
        AddPersonRequest r = (AddPersonRequest) request;
        repository.addPerson(r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
        refreshUseCase.execute(request);
    }

}
