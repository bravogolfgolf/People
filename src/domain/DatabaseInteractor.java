package domain;

import domain.Person;

import java.util.Map;

public interface DatabaseInteractor {
    void setPerson(Person person);

    Map<Integer, Person> getPeople();
}
