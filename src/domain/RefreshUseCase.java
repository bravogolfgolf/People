package domain;

import java.util.Map;
import java.util.StringJoiner;

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
        String[] responses = transformMapToStrings(people);
        RefreshResponse response = new RefreshResponse();
        response.people = responses;
        presenter.present(response);
    }

    private String[] transformMapToStrings(Map<Integer, Person> people) {
        String[] responses = new String[people.size()];
        int i = 0;
        for (Person person : people.values()) {
            StringJoiner message = new StringJoiner("|");
            message.add(String.valueOf(person.getId()));
            message.add(person.getFullName());
            message.add(person.getOccupation());
            message.add(String.valueOf(person.getAgeCategory()));
            message.add(String.valueOf(person.getEmploymentStatus()));
            message.add(String.valueOf(person.isUsCitizen()));
            message.add(person.getTaxId());
            message.add(person.getGender());
            responses[i++] = message.toString();
        }
        return responses;
    }

}
