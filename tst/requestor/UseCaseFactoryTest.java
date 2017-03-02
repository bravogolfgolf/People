package requestor;

import database.PersonRepositoryExportImport;
import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import exportimportgateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import responder.Presenter;
import responder.RefreshResponse;
import ui.RefreshViewModel;
import usecase.*;

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
    private final Map<String, Class<?>> useCases = new HashMap<>();
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

        assertTrue(factory.make("Refresh", presenter) instanceof RefreshUseCase);
        assertTrue(factory.make("AddPerson", presenter) instanceof AddPersonUseCase);
        assertTrue(factory.make("DeletePerson", presenter) instanceof DeletePersonUseCase);
        assertTrue(factory.make("Export", presenter) instanceof ExportUseCase);
        assertTrue(factory.make("Import", presenter) instanceof ImportUseCase);
    }

    private void setUseCases() {
        useCases.put("Refresh", RefreshUseCase.class);
        useCases.put("AddPerson", AddPersonUseCase.class);
        useCases.put("DeletePerson", DeletePersonUseCase.class);
        useCases.put("Export", ExportUseCase.class);
        useCases.put("Import", ImportUseCase.class);
    }

    private void registerUseCasePrimaryConstructorParameter() {
        constructorClasses.put("Refresh", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("AddPerson", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("DeletePerson", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("Export", new Class[]{ExportImport.class, Presenter.class});
        constructorClasses.put("Import", new Class[]{ExportImport.class, Presenter.class});
    }

    private void registerUseCasePrimaryConstructorObject() {
        constructorObjects.put("Refresh", new Object[]{repository});
        constructorObjects.put("AddPerson", new Object[]{repository});
        constructorObjects.put("DeletePerson", new Object[]{repository});
        constructorObjects.put("Export", new Object[]{exportImport});
        constructorObjects.put("Import", new Object[]{exportImport});
    }
}
