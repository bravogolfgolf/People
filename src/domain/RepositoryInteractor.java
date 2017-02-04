package domain;

import java.util.Map;

public interface RepositoryInteractor {
    void addPerson(Person person);

    Map<Integer, Person> getPeople();
}
