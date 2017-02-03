package domain;

public class Interactor implements InteractorController {

    private DatabaseInteractor database;
    private PresenterInteractor presenter;

    public void setDatabase(DatabaseInteractor database) {
        this.database = database;
    }

    public void setPresenter(PresenterInteractor presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handel(Request request) {
        Response response = new Response();
        Person person = new Person(request.fullName, request.occupation, request.ageCategory, request.employmentStatus, request.uSCitizen, request.taxId, request.gender);
        database.setPerson(person);
        response.people = database.getPeople();
        presenter.handle(response);
    }
}
