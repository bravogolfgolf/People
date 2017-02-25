package domain.importfile;

import entity.PersonTemplate;

import java.util.Map;

public interface ImportGateway {
    void setPeople(Map<Integer, PersonTemplate> people);
}
