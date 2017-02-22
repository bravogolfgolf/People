package main;

import data.PersonRepositoryInMemory;
import domain.addperson.AddPersonUseCase;
import domain.deleteperson.DeletePersonUseCase;
import domain.ExportImport;
import domain.exportfile.ExportUseCase;
import domain.importfile.ImportUseCase;
import domain.refresh.RefreshUseCase;
import org.junit.Test;
import ui.PersonTablePanelPresenter;
import ui.UseCaseFactory;

import static org.junit.Assert.assertTrue;

public class UseCaseFactoryImplTest {

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new ExportImport();
    private final ResponseBuilderImpl builder = new ResponseBuilderImpl();
    private final PersonTablePanelPresenter presenter = new PersonTablePanelPresenter();

    @Test
    public void makeMethodReturnsProperUseCase() {
        UseCaseFactory factory = new UseCaseFactoryImpl(repository, exportImport, builder, presenter);
        assertTrue(factory.make("RefreshUseCase") instanceof RefreshUseCase);
        assertTrue(factory.make("AddPersonUseCase") instanceof AddPersonUseCase);
        assertTrue(factory.make("DeletePersonUseCase") instanceof DeletePersonUseCase);
        assertTrue(factory.make("ExportUseCase") instanceof ExportUseCase);
        assertTrue(factory.make("ImportUseCase") instanceof ImportUseCase);
    }
}
