package domain;

import java.util.Collections;
import java.util.NoSuchElementException;

public class AddPersonUseCase implements UseCase {
    private final AddPersonGateway repository;
    private final Presenter presenter;

    public AddPersonUseCase(AddPersonGateway repository, Presenter presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        AddPersonRequest r = (AddPersonRequest) request;
        int id = determineId();
        Person person = new Person(id, r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
        repository.addPerson(person);
        presenter.present(repository.getPeople());
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
