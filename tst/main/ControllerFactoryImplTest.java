package main;

import data.PersonRepositoryInMemory;
import domain.ResponseBuilder;
import org.junit.Test;
import ui.*;
import ui.contoller.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ControllerFactoryImplTest {

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final ResponseBuilder responseBuilder = new ResponseBuilderImpl();
    private final UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(repository, responseBuilder);
    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final ControllerFactory factory = new ControllerFactoryImpl(requestBuilder, useCaseFactory);
    private final Map<Integer, Object> args = new HashMap<>();
    private final PersonTablePanelPresenter presenter = new PersonTablePanelPresenter();
    private final View view = records -> null;

    @Test
    public void makeMethodReturnsRefreshController() {
        Controller controller = factory.make("RefreshController", args, presenter, view);
        assertTrue(controller instanceof RefreshController);
    }

    @Test
    public void makeMethodReturnsAddPersonController() {
        EntryEvent formEvent = new EntryEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");
        args.put(1, formEvent);
        Controller controller = factory.make("AddPersonController", args, presenter, view);
        assertTrue(controller instanceof AddPersonController);
    }

    @Test
    public void makeMethodReturnsDeletePersonController() {
        int idToDelete = 1;
        args.put(1, idToDelete);
        Controller controller = factory.make("DeletePersonController", args, presenter, view);
        assertTrue(controller instanceof DeletePersonController);
    }

    @Test
    public void makeMethodReturnsExportController() {
        File file = new File("Export.per");
        args.put(1, file);
        Controller controller = factory.make("ExportController", args, presenter, view);
        assertTrue(controller instanceof ExportController);
    }

    @Test
    public void makeMethodReturnsImportController() {
        File file = new File("Import.per");
        args.put(1, file);
        Controller controller = factory.make("ImportController", args, presenter, view);
        assertTrue(controller instanceof ImportController);
    }
}