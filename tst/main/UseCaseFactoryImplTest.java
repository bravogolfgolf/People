package main;

import data.PersonRepositoryInMemory;
import domain.*;
import org.junit.Test;
import ui.UseCaseFactory;

import static org.junit.Assert.assertTrue;

public class UseCaseFactoryImplTest {

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new ExportImport();
    private final MainFramePresenter mainFrame = new MainFramePresenterDummy();
    private final Presenter presenter = new Presenter(mainFrame);

    @Test
    public void makeMethodReturnsProperUseCase() {
        UseCaseFactory factory = new UseCaseFactoryImpl(repository, exportImport, presenter);
        assertTrue(factory.make("AddPersonUseCase") instanceof AddPersonUseCase);
        assertTrue(factory.make("DeletePersonUseCase") instanceof DeletePersonUseCase);
        assertTrue(factory.make("ExportUseCase") instanceof ExportUseCase);
        assertTrue(factory.make("ImportUseCase") instanceof ImportUseCase);
    }

    private class MainFramePresenterDummy implements MainFramePresenter {
        @Override
        public void updatePersonTableModel(AddPersonRequest[] response) {

        }
    }
}
