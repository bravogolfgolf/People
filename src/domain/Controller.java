package domain;

public class Controller implements ControllerMainFrame {

    private InteractorController interactor;
    private final Request request = new Request();

    public void setInteractor(InteractorController interactor) {
        this.interactor = interactor;
    }

    @Override
    public void addPerson(FormEvent formEvent) {
        request.fullName = formEvent.getFullName();
        request.occupation = formEvent.getOccupation();
        request.ageCategory = formEvent.getAgeCategory();
        request.employmentStatus = formEvent.getEmploymentStatus();
        request.uSCitizen = formEvent.isUsCitizen();
        request.taxId = formEvent.getTaxId();
        request.gender = formEvent.getGender();

        interactor.addPerson(request);
    }
}
