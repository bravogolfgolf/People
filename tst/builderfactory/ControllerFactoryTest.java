package builderfactory;

import controller.*;
import org.junit.Test;
import responder.*;
import ui_swing.*;

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
        put("Update", UpdatePersonController.class);

    }};
    private final Class[] constructorClasses = new Class[]{Request.class, UseCase.class, Responder.class};
    private final ControllerFactory factory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, constructorClasses);
    private final Map<Integer, Object> requestArgs = new HashMap<>();

    @Test
    public void makeMethodReturnsRefreshController() {
        RefreshResponder responder = new RefreshPresenter(null);
        Controller controller = factory.make("Refresh", requestArgs, responder);
        assertTrue(controller instanceof RefreshController);
    }

    @Test
    public void makeMethodReturnsAddPersonController() {
        AddPersonResponder responder = new AddPersonPresenter(null);
        Controller controller = factory.make("AddPerson", requestArgs, responder);
        assertTrue(controller instanceof AddPersonController);
    }

    @Test
    public void makeMethodReturnsDeletePersonController() {
        DeletePersonResponder responder = new DeletePersonPresenter(null);
        Controller controller = factory.make("DeletePerson", requestArgs, responder);
        assertTrue(controller instanceof DeletePersonController);
    }

    @Test
    public void makeMethodReturnsExportController() {
        ExportResponder responder = new ExportPresenter(null);
        Controller controller = factory.make("Export", requestArgs, responder);
        assertTrue(controller instanceof ExportController);
    }

    @Test
    public void makeMethodReturnsImportController() {
        ImportResponder responder = new ImportPresenter(null);
        Controller controller = factory.make("Import", requestArgs, responder);
        assertTrue(controller instanceof ImportController);
    }

    @Test
    public void makeMethodReturnsUpdatePersonController() {
        UpdatePersonResponder responder = new UpdatePersonPresenter(null);
        Controller controller = factory.make("Update", requestArgs, responder);
        assertTrue(controller instanceof UpdatePersonController);
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