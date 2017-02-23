package domain.addperson;

import domain.InputBoundary;
import domain.Person;
import domain.Request;

import java.util.Collections;
import java.util.NoSuchElementException;

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
        int id = determineId();
        Person person = new Person(id, r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
        repository.addPerson(person);
        refreshUseCase.execute(request);
    }

    private int determineId() {
        int id;
        try {
            id = Collections.max(repository.getPeople().keySet()) + 1;
        } catch (NoSuchElementException e) {
            id = 1;
        }
        return id;
    }
}
