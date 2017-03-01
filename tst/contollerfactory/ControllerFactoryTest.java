package contollerfactory;

import contoller.*;
import database.PersonRepositoryExportImport;
import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import exportimportgateway.ExportImport;
import org.junit.Test;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
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
        put("RefreshUseCase", new Object[]{repository});
        put("AddPersonUseCase", new Object[]{repository});
        put("DeletePersonUseCase", new Object[]{repository});
        put("ExportUseCase", new Object[]{exportImport});
        put("ImportUseCase", new Object[]{exportImport});
    }};
    private final Map<String, Class<? extends UseCase>> useCases = new HashMap<String, Class<? extends UseCase>>() {{
        put("RefreshUseCase", RefreshUseCase.class);
        put("AddPersonUseCase", AddPersonUseCase.class);
        put("DeletePersonUseCase", DeletePersonUseCase.class);
        put("ExportUseCase", ExportUseCase.class);
        put("ImportUseCase", ImportUseCase.class);
    }};
    private final Map<String, Class<?>[]> useCaseConstructorClasses = new HashMap<String, Class<?>[]>() {{
        put("RefreshUseCase", new Class[]{PersonRepository.class, Presenter.class});
        put("AddPersonUseCase", new Class[]{PersonRepository.class, Presenter.class});
        put("DeletePersonUseCase", new Class[]{PersonRepository.class, Presenter.class});
        put("ExportUseCase", new Class[]{ExportImport.class, Presenter.class});
        put("ImportUseCase", new Class[]{ExportImport.class, Presenter.class});
    }};
    private final UseCaseFactory useCaseFactory = new UseCaseFactory(useCases, useCaseConstructorClasses, constructorObjects);
    private final Map<String, Class<? extends Request>> requests = new HashMap<String, Class<? extends Request>>() {{
        put("RefreshRequest", RefreshRequest.class);
        put("AddPersonRequest", AddPersonRequest.class);
        put("DeletePersonRequest", DeletePersonRequest.class);
        put("ExportRequest", ExportRequest.class);
        put("ImportRequest", ImportRequest.class);
    }};
    private final RequestBuilder requestBuilder = new RequestBuilder(requests);
    private final Map<String, Class<? extends Controller>> controllers = new HashMap<String, Class<? extends Controller>>() {{
        put("RefreshController", RefreshController.class);
        put("AddPersonController", AddPersonController.class);
        put("DeletePersonController", DeletePersonController.class);
        put("ExportController", ExportController.class);
        put("ImportController", ImportController.class);
    }};
    private final Map<String, Class<?>[]> constructorClasses = new HashMap<String, Class<?>[]>() {{
        put("RefreshController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        put("AddPersonController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        put("DeletePersonController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        put("ExportController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        put("ImportController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
    }};
    private final ControllerFactory factory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, constructorClasses);
    private final Map<Integer, Object> requestArgs = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = null;


    @Test
    public void makeMethodReturnsRefreshController() {
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("RefreshController", factoryArgs);
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
        Controller controller = factory.make("AddPersonController", factoryArgs);
        assertTrue(controller instanceof AddPersonController);
    }

    @Test
    public void makeMethodReturnsDeletePersonController() {
        int idToDelete = 1;
        requestArgs.put(0, idToDelete);
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("DeletePersonController", factoryArgs);
        assertTrue(controller instanceof DeletePersonController);
    }

    @Test
    public void makeMethodReturnsExportController() {
        File file = new File("Export.per");
        requestArgs.put(0, file);
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("ExportController", factoryArgs);
        assertTrue(controller instanceof ExportController);
    }

    @Test
    public void makeMethodReturnsImportController() {
        File file = new File("Import.per");
        requestArgs.put(0, file);
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("ImportController", factoryArgs);
        assertTrue(controller instanceof ImportController);
    }
}