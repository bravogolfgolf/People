package domain;

import java.util.Map;

public interface DeletePersonGateway {
    void deletePerson(int id);

    Map<Integer, Person> getPeople();
}
