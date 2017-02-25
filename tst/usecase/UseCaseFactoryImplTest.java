package usecase;

import database.PersonRepository;
import database.PersonRepositoryInMemory;
import respondor.PersonRecord;
import respondor.Presenter;
import respondor.Response;
import usecase.addperson.AddPersonUseCase;
import usecase.deleteperson.DeletePersonUseCase;
import usecase.exportfile.ExportUseCase;
import usecase.importfile.ImportUseCase;
import usecase.refresh.RefreshUseCase;
import org.junit.Test;
import requestor.UseCaseFactory;

import static org.junit.Assert.assertTrue;

public class UseCaseFactoryImplTest {

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final Presenter presenter = new Presenter() {
        @Override
        public void present(Response response) {
        }

        @Override
        public PersonRecord[] getViewModel() {
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
