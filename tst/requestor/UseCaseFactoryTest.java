package requestor;

import database.PersonRepositoryExportImport;
import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import exportimportgateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import responder.Presenter;
import responder.RefreshResponse;
import responder.RefreshViewModel;
import usecase.addperson.AddPersonUseCase;
import usecase.deleteperson.DeletePersonUseCase;
import usecase.exportfile.ExportUseCase;
import usecase.importfile.ImportUseCase;
import usecase.refresh.RefreshUseCase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UseCaseFactoryTest {
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final PersonRepositoryExportImport exportImport = new PersonRepositoryExportImport(repository);
    private final Presenter presenter = new Presenter() {
        @Override
        public void present(RefreshResponse response) {
        }

        @Override
        public RefreshViewModel[] getViewModel() {
            return null;
        }
    };
    private UseCaseFactory factory;
    private final Map<String, Class<? extends UseCase>> useCases = new HashMap<>();
    private final Map<String, Class<?>[]> constructorClasses = new HashMap<>();
    private final Map<String, Object[]> constructorObjects = new HashMap<>();


    @Before
    public void setUp() throws Exception {
        factory = new UseCaseFactory(useCases, constructorClasses, constructorObjects);
    }

    @Test
    public void makeMethodReturnsProperUseCase() {
        setUseCases();
        registerUseCasePrimaryConstructorParameter();
        registerUseCasePrimaryConstructorObject();

        assertTrue(factory.make("RefreshUseCase", presenter) instanceof RefreshUseCase);
        assertTrue(factory.make("AddPersonUseCase", presenter) instanceof AddPersonUseCase);
        assertTrue(factory.make("DeletePersonUseCase", presenter) instanceof DeletePersonUseCase);
        assertTrue(factory.make("ExportUseCase", presenter) instanceof ExportUseCase);
        assertTrue(factory.make("ImportUseCase", presenter) instanceof ImportUseCase);
    }

    private void setUseCases() {
        useCases.put("RefreshUseCase", RefreshUseCase.class);
        useCases.put("AddPersonUseCase", AddPersonUseCase.class);
        useCases.put("DeletePersonUseCase", DeletePersonUseCase.class);
        useCases.put("ExportUseCase", ExportUseCase.class);
        useCases.put("ImportUseCase", ImportUseCase.class);
    }

    private void registerUseCasePrimaryConstructorParameter() {
        constructorClasses.put("RefreshUseCase", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("AddPersonUseCase", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("DeletePersonUseCase", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("ExportUseCase", new Class[]{ExportImport.class, Presenter.class});
        constructorClasses.put("ImportUseCase", new Class[]{ExportImport.class, Presenter.class});
    }

    private void registerUseCasePrimaryConstructorObject() {
        constructorObjects.put("RefreshUseCase", new Object[]{repository});
        constructorObjects.put("AddPersonUseCase", new Object[]{repository});
        constructorObjects.put("DeletePersonUseCase", new Object[]{repository});
        constructorObjects.put("ExportUseCase", new Object[]{exportImport});
        constructorObjects.put("ImportUseCase", new Object[]{exportImport});
    }
}
