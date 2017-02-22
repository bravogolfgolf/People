package data;

import domain.*;

public abstract class PersonRepository
        implements RefreshGateway, AddPersonGateway, DeletePersonGateway, ExportGateway, ImportGateway {
}
