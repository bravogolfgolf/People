package ui;

import domain.InteractorController;
import domain.AddPersonRequest;

import java.io.File;
import java.io.IOException;

public class ControllerImpl implements Controller {

    private InteractorController interactor;
    private final AddPersonRequest request = new AddPersonRequest();

    public void setInteractor(InteractorController interactor) {
        this.interactor = interactor;
    }

    void addPerson(EntryEvent formEvent) {
        request.fullName = formEvent.getFullName();
        request.occupation = formEvent.getOccupation();
        request.ageCategory = formEvent.getAgeCategory();
        request.employmentStatus = formEvent.getEmploymentStatus();
        request.uSCitizen = formEvent.isUsCitizen();
        request.taxId = formEvent.getTaxId();
        request.gender = formEvent.getGender();

        interactor.addPerson(request);
    }

    void exportRepository(File file) throws IOException {
        interactor.exportRepository(file);
    }

    void loadRepository(File file) throws IOException, ClassNotFoundException {
        interactor.loadRepository(file);
    }

    void deletePerson(int id) {
        interactor.deletePerson(id);
    }

    @Override
    public void execute() {

    }
}
