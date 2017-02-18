package domain;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Interactor implements InteractorController {

    private RepositoryInteractor repository;
    private PresenterInteractor presenter;
    private PersistentInteractor persistent;
    private int nextID;

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
    public void addPerson(PersonMessage request) throws SQLException, ClassNotFoundException {
        setNextID();
        Person person = new Person(nextID, request.fullName, request.occupation, request.ageCategory, request.employmentStatus, request.uSCitizen, request.taxId, request.gender);
        repository.addPerson(person);
        presenter.presentPeople(repository.getPeople());
    }

    private void setNextID() throws SQLException, ClassNotFoundException {
        try {
            nextID = Collections.max(repository.getPeople().keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextID = 1;
        }
    }

    @Override
    public void exportRepository(File file) throws IOException, SQLException, ClassNotFoundException {
        persistent.export(repository.getPeople(), file);
    }

    @Override
    public void loadRepository(File file) throws IOException, ClassNotFoundException, SQLException {
        repository.setPeople(persistent.getImport(file));
        presenter.presentPeople(repository.getPeople());
    }

    @Override
    public void deletePerson(int id) throws SQLException, ClassNotFoundException {
        repository.deletePerson(id);
        presenter.presentPeople(repository.getPeople());
    }
}
