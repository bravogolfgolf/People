package main;

import data.PersonRepositoryInMemory;
import domain.addperson.AddPersonUseCase;
import domain.deleteperson.DeletePersonUseCase;
import domain.exportfile.ExportUseCase;
import domain.importfile.ImportUseCase;
import domain.refresh.RefreshUseCase;
import org.junit.Test;
import ui.PersonTablePanelPresenter;
import ui.contoller.UseCaseFactory;

import static org.junit.Assert.assertTrue;

public class UseCaseFactoryImplTest {

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final ResponseBuilderImpl builder = new ResponseBuilderImpl();
    private final PersonTablePanelPresenter presenter = new PersonTablePanelPresenter();

    @Test
    public void makeMethodReturnsProperUseCase() {
        UseCaseFactory factory = new UseCaseFactoryImpl(repository, builder);
        assertTrue(factory.make("RefreshUseCase", presenter) instanceof RefreshUseCase);
        assertTrue(factory.make("AddPersonUseCase", presenter) instanceof AddPersonUseCase);
        assertTrue(factory.make("DeletePersonUseCase", presenter) instanceof DeletePersonUseCase);
        assertTrue(factory.make("ExportUseCase", presenter) instanceof ExportUseCase);
        assertTrue(factory.make("ImportUseCase", presenter) instanceof ImportUseCase);
    }
}
