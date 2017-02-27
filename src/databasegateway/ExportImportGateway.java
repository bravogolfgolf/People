package databasegateway;

import database.Person;

import java.util.Collection;
import java.util.List;

public interface ExportImportGateway {
    void fromImport(List<Person> people);

    Collection<Person> forExport();
}
