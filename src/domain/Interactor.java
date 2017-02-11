package domain;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Interactor implements InteractorController {

    private RepositoryInteractor repository;
    private PresenterInteractor presenter;
    private PersistentInteractor persistent;

    public void setRepository(RepositoryInteractor repository) {
        this.repository = repository;
    }

    public void setPersistent(PersistentInteractor persistent) {
        this.persistent = persistent;
    }

    public void setPresenter(PresenterInteractor presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(PersonMessage request) {
        Person person = new Person(request.fullName, request.occupation, request.ageCategory, request.employmentStatus, request.uSCitizen, request.taxId, request.gender);
        repository.addPerson(person);
        presenter.presentPeople(repository.getPeople());
    }

    @Override
    public void exportRepository(File file) throws IOException {
        persistent.export(repository.getPeople(), file);
    }

    @Override
    public void loadRepository(File file) throws IOException, ClassNotFoundException {
        repository.setPeople(persistent.getImport(file));
        int counter = Collections.max(repository.getPeople().keySet()) + 1;
        Person.setCounter(counter);
        presenter.presentPeople(repository.getPeople());
    }

    @Override
    public void deletePerson(int id) {
        repository.deletePerson(id);
        presenter.presentPeople(repository.getPeople());
    }
}
