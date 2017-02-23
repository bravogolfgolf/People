package main;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.PersonTableModelRecord;
import domain.Presenter;
import domain.Response;
import domain.addperson.AddPersonUseCase;
import domain.deleteperson.DeletePersonUseCase;
import domain.exportfile.ExportUseCase;
import domain.importfile.ImportUseCase;
import domain.refresh.RefreshUseCase;
import org.junit.Test;
import ui.contoller.UseCaseFactory;

import static org.junit.Assert.assertTrue;

public class UseCaseFactoryImplTest {

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final Presenter presenter = new Presenter() {
        @Override
        public void present(Response response) {
        }

        @Override
        public PersonTableModelRecord[] getViewModel() {
            return null;
        }
    };

    @Test
    public void makeMethodReturnsProperUseCase() {
        UseCaseFactory factory = new UseCaseFactoryImpl(repository);
        assertTrue(factory.make("RefreshUseCase", presenter) instanceof RefreshUseCase);
        assertTrue(factory.make("AddPersonUseCase", presenter) instanceof AddPersonUseCase);
        assertTrue(factory.make("DeletePersonUseCase", presenter) instanceof DeletePersonUseCase);
        assertTrue(factory.make("ExportUseCase", presenter) instanceof ExportUseCase);
        assertTrue(factory.make("ImportUseCase", presenter) instanceof ImportUseCase);
    }
}
