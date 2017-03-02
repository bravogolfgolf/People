package contollerfactory;

import controller.*;
import database.PersonRepositoryExportImport;
import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import exportimportgateway.ExportImport;
import org.junit.Test;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.Presenter;
import ui.PersonTablePanelPresenter;
import usecase.*;
import view.View;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ControllerFactoryTest {

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final PersonRepositoryExportImport exportImport = new PersonRepositoryExportImport(repository);
    private final Map<String, Object[]> constructorObjects = new HashMap<String, Object[]>() {{
        put("Refresh", new Object[]{repository});
        put("AddPerson", new Object[]{repository});
        put("DeletePerson", new Object[]{repository});
        put("Export", new Object[]{exportImport});
        put("Import", new Object[]{exportImport});
    }};
    private final Map<String, Class<?>> useCases = new HashMap<String, Class<?>>() {{
        put("Refresh", RefreshUseCase.class);
        put("AddPerson", AddPersonUseCase.class);
        put("DeletePerson", DeletePersonUseCase.class);
        put("Export", ExportUseCase.class);
        put("Import", ImportUseCase.class);
    }};
    private final Map<String, Class<?>[]> useCaseConstructorClasses = new HashMap<String, Class<?>[]>() {{
        put("Refresh", new Class[]{PersonRepository.class, Presenter.class});
        put("AddPerson", new Class[]{PersonRepository.class, Presenter.class});
        put("DeletePerson", new Class[]{PersonRepository.class, Presenter.class});
        put("Export", new Class[]{ExportImport.class, Presenter.class});
        put("Import", new Class[]{ExportImport.class, Presenter.class});
    }};
    private final UseCaseFactory useCaseFactory = new UseCaseFactory(useCases, useCaseConstructorClasses, constructorObjects);
    private final Map<String, Class<?>> requests = new HashMap<String, Class<?>>() {{
        put("Refresh", RefreshRequest.class);
        put("AddPerson", AddPersonRequest.class);
        put("DeletePerson", DeletePersonRequest.class);
        put("Export", ExportRequest.class);
        put("Import", ImportRequest.class);
    }};
    private final RequestBuilder requestBuilder = new RequestBuilder(requests);
    private final Map<String, Class<?>> controllers = new HashMap<String, Class<?>>() {{
        put("Refresh", RefreshController.class);
        put("AddPerson", AddPersonController.class);
        put("DeletePerson", DeletePersonController.class);
        put("Export", ExportController.class);
        put("Import", ImportController.class);
    }};
    private final Map<String, Class<?>[]> constructorClasses = new HashMap<String, Class<?>[]>() {{
        put("Refresh", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        put("AddPerson", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        put("DeletePerson", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        put("Export", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        put("Import", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
    }};
    private final ControllerFactory factory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, constructorClasses);
    private final Map<Integer, Object> requestArgs = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = null;


    @Test
    public void makeMethodReturnsRefreshController() {
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("Refresh", factoryArgs);
        assertTrue(controller instanceof RefreshController);
    }

    @Test
    public void makeMethodReturnsAddPersonController() {
        requestArgs.put(0, "Full Name");
        requestArgs.put(1, "Occupation");
        requestArgs.put(2, 0);
        requestArgs.put(3, 0);
        requestArgs.put(4, true);
        requestArgs.put(5, "Tax ID");
        requestArgs.put(6, "Gender");
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("AddPerson", factoryArgs);
        assertTrue(controller instanceof AddPersonController);
    }

    @Test
    public void makeMethodReturnsDeletePersonController() {
        int idToDelete = 1;
        requestArgs.put(0, idToDelete);
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("DeletePerson", factoryArgs);
        assertTrue(controller instanceof DeletePersonController);
    }

    @Test
    public void makeMethodReturnsExportController() {
        File file = new File("Export.per");
        requestArgs.put(0, file);
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("Export", factoryArgs);
        assertTrue(controller instanceof ExportController);
    }

    @Test
    public void makeMethodReturnsImportController() {
        File file = new File("Import.per");
        requestArgs.put(0, file);
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("Import", factoryArgs);
        assertTrue(controller instanceof ImportController);
    }
}