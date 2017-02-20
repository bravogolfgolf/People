package domain;

import java.util.Map;

public class RefreshUseCase implements UseCase {
    private final RefreshGateway repository;
    private final Presenter presenter;

    public RefreshUseCase(RefreshGateway repository, Presenter presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        Map<Integer, Person> people = repository.getPeople();
        RefreshResponse[] responses = new RefreshResponse[people.size()];
        int i = 0;
        for (Person person: people.values()) {
            RefreshResponse message = new RefreshResponse();
            message.id = person.getId();
            message.fullName = person.getFullName();
            message.occupation = person.getOccupation();
            message.ageCategory = person.getAgeCategory();
            message.employmentStatus = person.getEmploymentStatus();
            message.uSCitizen = person.isUsCitizen();
            message.taxId = person.getTaxId();
            message.gender = person.getGender();
            responses[i++] = message;
        }
        presenter.present(responses);
    }
}
