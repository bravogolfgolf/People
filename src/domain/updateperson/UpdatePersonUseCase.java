package domain.updateperson;

import domain.InputBoundary;
import domain.Person;
import domain.Request;

public class UpdatePersonUseCase implements InputBoundary {
    private final UpdatePersonGateway repository;
    private final InputBoundary refreshUseCase;

    public UpdatePersonUseCase(UpdatePersonGateway repository, InputBoundary refreshUseCase) {
        this.repository = repository;
        this.refreshUseCase = refreshUseCase;
    }

    @Override
    public void execute(Request request) {
        UpdatePersonRequest r = (UpdatePersonRequest) request;
        Person person = new Person(r.id, r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
        repository.updatePerson(person);
        refreshUseCase.execute(request);
    }
}
