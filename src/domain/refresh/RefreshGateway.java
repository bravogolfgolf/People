package domain.refresh;

import entity.PersonTemplate;

import java.util.Map;

public interface RefreshGateway {
    Map<Integer, PersonTemplate> getPeople();
}
