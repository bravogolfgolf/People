package domain;

import java.util.Map;

public interface AddPersonGateway {
    void addPerson(Person person);

    Map<Integer, Person> getPeople();
}
