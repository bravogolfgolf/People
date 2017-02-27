package databasegateway;

import entity.PersonTemplate;

import java.util.List;

public interface RefreshGateway {
    List<PersonTemplate> findAll();
}
