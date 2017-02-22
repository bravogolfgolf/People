package data;

import domain.addperson.AddPersonGateway;
import domain.deleteperson.DeletePersonGateway;
import domain.exportfile.ExportGateway;
import domain.importfile.ImportGateway;
import domain.refresh.RefreshGateway;

public abstract class PersonRepository
        implements RefreshGateway, AddPersonGateway, DeletePersonGateway, ExportGateway, ImportGateway {
}
