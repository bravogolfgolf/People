package builderfactory;

import controller.*;
import org.junit.Test;
import responder.*;
import ui_swing.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ControllerFactoryTest {
    private final RequestBuilder requestBuilder = new RequestBuilderStub(new HashMap<>());
    private final UseCaseFactory useCaseFactory = new UseCaseFactoryStub(new HashMap<>(), new HashMap<>(), new HashMap<>());
    private final Map<String, Class> controllers = new HashMap<String, Class>() {{
        put("Refresh", RefreshController.class);
        put("AddPerson", AddPersonController.class);
        put("DeletePerson", DeletePersonController.class);
        put("Export", ExportController.class);
        put("Import", ImportController.class);
    }};
    private final Map<String, Class[]> constructorClasses = new HashMap<String, Class[]>() {{
        put("Refresh", new Class[]{Request.class, UseCase.class, RefreshResponder.class, View.class});
        put("AddPerson", new Class[]{Request.class, UseCase.class, AddPersonResponder.class, View.class});
        put("DeletePerson", new Class[]{Request.class, UseCase.class, DeletePersonResponder.class, View.class});
        put("Export", new Class[]{Request.class, UseCase.class, ExportResponder.class, View.class});
        put("Import", new Class[]{Request.class, UseCase.class, ImportResponder.class, View.class});
    }};
    private final ControllerFactory factory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, constructorClasses);
    private final Map<Integer, Object> requestArgs = new HashMap<>();

    @Test
    public void makeMethodReturnsRefreshController() {
        View view = new RefreshView();
        RefreshResponder presenter = new RefreshPresenter(view);
        Controller controller = factory.make("Refresh", requestArgs, presenter, view);
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
        View view = new AddPersonView();
        AddPersonResponder presenter = new AddPersonPresenter(view);
        Controller controller = factory.make("AddPerson", requestArgs, presenter, view);
        assertTrue(controller instanceof AddPersonController);
    }

    @Test
    public void makeMethodReturnsDeletePersonController() {
        int idToDelete = 1;
        requestArgs.put(0, idToDelete);
        View view = new DeletePersonView();
        DeletePersonResponder presenter = new DeletePersonPresenter(view);
        Controller controller = factory.make("DeletePerson", requestArgs, presenter, view);
        assertTrue(controller instanceof DeletePersonController);
    }

    @Test
    public void makeMethodReturnsExportController() {
        File file = new File("Export.per");
        requestArgs.put(0, file);
        View view = new ExportView();
        ExportResponder presenter = new ExportPresenter(view);
        Controller controller = factory.make("Export", requestArgs, presenter, view);
        assertTrue(controller instanceof ExportController);
    }

    @Test
    public void makeMethodReturnsImportController() {
        File file = new File("Import.per");
        requestArgs.put(0, file);
        View view = new ImportView();
        ImportResponder presenter = new ImportPresenter(view);
        Controller controller = factory.make("Import", requestArgs, presenter, view);
        assertTrue(controller instanceof ImportController);
    }

    private class RequestBuilderStub extends RequestBuilder {
        RequestBuilderStub(Map<String, Class> requests) {
            super(requests);
        }

        @Override
        public Request make(String request, Map<Integer, Object> args) {
            return new Request();
        }
    }

    private class UseCaseFactoryStub extends UseCaseFactory {
        UseCaseFactoryStub(Map<String, Class> useCases, Map<String, Class[]> useCaseConstructorClasses, Map<String, Object[]> constructorObjects) {
            super(useCases, useCaseConstructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Object responder) {
            return new UseCase();
        }
    }
}