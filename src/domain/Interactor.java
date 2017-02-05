package domain;

import data.Persistent;

import java.io.File;
import java.io.IOException;

public class Interactor implements InteractorController {

    private RepositoryInteractor repository;
    private PresenterInteractor presenter;

    public void setRepository(RepositoryInteractor repository) {
        this.repository = repository;
    }

    public void setPresenter(PresenterInteractor presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(PersonMessage request) {
        Person person = new Person(request.fullName, request.occupation, request.ageCategory, request.employmentStatus, request.uSCitizen, request.taxId, request.gender);
        repository.addPerson(person);
        presenter.addPerson(repository.getPeople());
    }

    @Override
    public void exportRepository(File file) throws IOException {
        Persistent.export(repository.getPeople(), file);
    }

    @Override
    public void loadRepository(File file) throws IOException {
        repository.setPeople(Persistent.load(file));
        int counter = repository.getPeople().size();
        Person.setCounter(counter);
        presenter.addPerson(repository.getPeople());
    }
}
