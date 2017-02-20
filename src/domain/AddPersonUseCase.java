package domain;

import java.util.Collections;
import java.util.NoSuchElementException;

public class AddPersonUseCase implements UseCase {
    private final AddPersonGateway repository;

    public AddPersonUseCase(AddPersonGateway repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request) {
        AddPersonRequest r = (AddPersonRequest) request;
        int id = determineId();
        Person person = new Person(id, r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
        repository.addPerson(person);
    }

    private int determineId() {
        int id;
        try {
            id = Collections.max(repository.getPeople().keySet()) + 1;
        } catch (NoSuchElementException e) {
            id = 1;
        }
        return id ;
    }
}
