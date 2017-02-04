package domain;

import java.util.Map;

public interface RepositoryInteractor {
    void setPerson(Person person);

    Map<Integer, Person> getPeople();
}
