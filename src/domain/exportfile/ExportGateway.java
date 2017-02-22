package domain.exportfile;

import domain.Person;

import java.util.Map;

public interface ExportGateway {
    Map<Integer, Person> getPeople();
}
