package domain.exportfile;

import entity.PersonTemplate;

import java.util.Map;

public interface ExportGateway {
    Map<Integer, PersonTemplate> getPeople();
}
