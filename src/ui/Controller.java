package ui;

import domain.InteractorController;
import domain.PersonMessage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    private InteractorController interactor;
    private final PersonMessage request = new PersonMessage();

    public void setInteractor(InteractorController interactor) {
        this.interactor = interactor;
    }

    void addPerson(EntryEvent formEvent) throws SQLException, ClassNotFoundException {
        request.fullName = formEvent.getFullName();
        request.occupation = formEvent.getOccupation();
        request.ageCategory = formEvent.getAgeCategory();
        request.employmentStatus = formEvent.getEmploymentStatus();
        request.uSCitizen = formEvent.isUsCitizen();
        request.taxId = formEvent.getTaxId();
        request.gender = formEvent.getGender();

        interactor.addPerson(request);
    }

    void exportRepository(File file) throws IOException, SQLException, ClassNotFoundException {
        interactor.exportRepository(file);
    }

    void loadRepository(File file) throws IOException, ClassNotFoundException, SQLException {
        interactor.loadRepository(file);
    }

    void deletePerson(int id) throws SQLException, ClassNotFoundException {
        interactor.deletePerson(id);
    }
}
