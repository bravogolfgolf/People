package data;

import domain.AddPersonGateway;
import domain.DeletePersonGateway;
import domain.ExportGateway;
import domain.ImportGateway;

public abstract class RepositoryInteractor
        implements AddPersonGateway, DeletePersonGateway, ExportGateway, ImportGateway {
}
