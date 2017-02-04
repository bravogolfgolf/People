package domain;

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
    public void addPerson(Request request) {
        Person person = new Person(request.fullName, request.occupation, request.ageCategory, request.employmentStatus, request.uSCitizen, request.taxId, request.gender);
        repository.addPerson(person);
        presenter.handle(repository.getPeople());
    }
}
