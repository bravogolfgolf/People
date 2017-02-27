package databasegateway;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class PersonRepository implements RefreshGateway, AddPersonGateway, UpdatePersonGateway, DeletePersonGateway, ExportImportGateway {

    protected int determineId(Set<Integer> people) {
        int id;
        try {
            id = Collections.max(people) + 1;
        } catch (NoSuchElementException e) {
            id = 1;
        }
        return id;
    }
}
