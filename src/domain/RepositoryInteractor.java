package domain;

import java.util.Map;

interface RepositoryInteractor {
    void addPerson(Person person);

    Map<Integer, Person> getPeople();
}
