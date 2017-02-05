package domain;

import java.io.File;
import java.io.IOException;

public class Controller implements ControllerMainFrame {

    private InteractorController interactor;
    private final PersonMessage request = new PersonMessage();

    public void setInteractor(InteractorController interactor) {
        this.interactor = interactor;
    }

    @Override
    public void addPerson(FormEventController formEvent) {
        request.fullName = formEvent.getFullName();
        request.occupation = formEvent.getOccupation();
        request.ageCategory = formEvent.getAgeCategory();
        request.employmentStatus = formEvent.getEmploymentStatus();
        request.uSCitizen = formEvent.isUsCitizen();
        request.taxId = formEvent.getTaxId();
        request.gender = formEvent.getGender();

        interactor.addPerson(request);
    }

    @Override
    public void exportRepository(File file) throws IOException {
        interactor.exportRepository(file);
    }

    @Override
    public void loadRepository(File file) throws IOException {
        interactor.loadRepository(file);
    }
}
