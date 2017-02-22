package domain.importfile;

import domain.Person;

import java.util.Map;

public interface ImportGateway {
    void setPeople(Map<Integer, Person> people);
}
