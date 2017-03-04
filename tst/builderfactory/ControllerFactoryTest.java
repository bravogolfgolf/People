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
        put("Refresh", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, RefreshResponder.class, View.class});
        put("AddPerson", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, AddPersonResponder.class, View.class});
        put("DeletePerson", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, DeletePersonResponder.class, View.class});
        put("Export", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, ExportResponder.class, View.class});
        put("Import", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, ImportResponder.class, View.class});
    }};
    private final ControllerFactory factory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, constructorClasses);
    private final Map<Integer, Object> requestArgs = new HashMap<>();

    @Test
    public void makeMethodReturnsRefreshController() {
        RefreshResponder presenter = new RefreshPresenter();
        View view = new RefreshView();
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
        AddPersonResponder presenter = new AddPersonPresenter();
        View view = new AddPersonView();
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("AddPerson", factoryArgs);
        assertTrue(controller instanceof AddPersonController);
    }

    @Test
    public void makeMethodReturnsDeletePersonController() {
        int idToDelete = 1;
        requestArgs.put(0, idToDelete);
        DeletePersonResponder presenter = new DeletePersonPresenter();
        View view = new DeletePersonView();
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("DeletePerson", factoryArgs);
        assertTrue(controller instanceof DeletePersonController);
    }

    @Test
    public void makeMethodReturnsExportController() {
        File file = new File("Export.per");
        requestArgs.put(0, file);
        ExportResponder presenter = new ExportPresenter();
        View view = new ExportView();
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("Export", factoryArgs);
        assertTrue(controller instanceof ExportController);
    }

    @Test
    public void makeMethodReturnsImportController() {
        File file = new File("Import.per");
        requestArgs.put(0, file);
        ImportResponder presenter = new ImportPresenter();
        View view = new ImportView();
        Object[] factoryArgs = new Object[]{requestArgs, presenter, view};
        Controller controller = factory.make("Import", factoryArgs);
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