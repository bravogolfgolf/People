package domain.refresh;

import domain.Person;

import java.util.Map;

public interface RefreshGateway {
    Map<Integer, Person> getPeople();
}
