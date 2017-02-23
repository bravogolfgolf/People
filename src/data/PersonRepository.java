package data;

import domain.addperson.AddPersonGateway;
import domain.deleteperson.DeletePersonGateway;
import domain.exportfile.ExportGateway;
import domain.importfile.ImportGateway;
import domain.refresh.RefreshGateway;
import domain.updateperson.UpdatePersonGateway;

public abstract class PersonRepository
        implements RefreshGateway, AddPersonGateway, UpdatePersonGateway, DeletePersonGateway, ExportGateway, ImportGateway {
}
