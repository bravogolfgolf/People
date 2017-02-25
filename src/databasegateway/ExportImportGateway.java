package databasegateway;

import entity.PersonTemplate;

import java.util.Map;

public interface ExportImportGateway {
    void setPeople(Map<Integer, PersonTemplate> map);

    Map<Integer, PersonTemplate> getPeople();
}
