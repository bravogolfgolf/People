package builderfactory;

import database.PersonRepositoryExportImport;
import gateway.ExportImport;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.*;
import usecase.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UseCaseFactoryTest {
    private final PersonRepository repository = null;
    private final PersonRepositoryExportImport exportImport = null;
    private final RefreshResponder presenter = null;
    private final Map<String, Class> useCases = new HashMap<>();
    private final Map<String, Class[]> constructorClasses = new HashMap<>();
    private final Map<String, Object[]> constructorObjects = new HashMap<>();
    private final UseCaseFactory factory = new UseCaseFactory(useCases, constructorClasses, constructorObjects);

    @Before
    public void setUp() throws Exception {
        setUseCases();
        setConstructorClasses();
        setConstructorObjects();
    }

    @Test
    public void makeMethodReturnsProperUseCase() {
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

    private void setConstructorClasses() {
        constructorClasses.put("Refresh", new Class[]{PersonRepository.class, RefreshResponder.class});
        constructorClasses.put("AddPerson", new Class[]{PersonRepository.class, AddPersonResponder.class});
        constructorClasses.put("DeletePerson", new Class[]{PersonRepository.class, DeletePersonResponder.class});
        constructorClasses.put("Export", new Class[]{ExportImport.class, ExportResponder.class});
        constructorClasses.put("Import", new Class[]{ExportImport.class, ImportResponder.class});
    }

    private void setConstructorObjects() {
        constructorObjects.put("Refresh", new Object[]{repository});
        constructorObjects.put("AddPerson", new Object[]{repository});
        constructorObjects.put("DeletePerson", new Object[]{repository});
        constructorObjects.put("Export", new Object[]{exportImport});
        constructorObjects.put("Import", new Object[]{exportImport});
    }
}
