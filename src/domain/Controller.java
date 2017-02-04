package domain;

public class Controller {

    private InteractorController interactor;
    private final Request request = new Request();

    public void setInteractor(InteractorController interactor) {
        this.interactor = interactor;
    }


    public void handle(FormEvent formEvent) {
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
