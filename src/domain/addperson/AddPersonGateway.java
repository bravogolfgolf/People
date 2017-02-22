package domain.addperson;

import domain.Person;

import java.util.Map;

public interface AddPersonGateway {
    void addPerson(Person person);

    Map<Integer, Person> getPeople();
}
