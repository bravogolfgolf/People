package main;

import data.PersonRepositoryInMemory;
import domain.ExportImport;
import org.junit.Before;
import org.junit.Test;
import ui.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ControllerFactoryImplTest {

    private final RequestBuilder builder = new RequestBuilderImpl();

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new ExportImport();
    private final MainFramePresenter mainFrame = new MainFrameDummy();
    private final PresenterImpl presenter = new PresenterImpl(mainFrame);

    private final UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(repository, exportImport, presenter);
    private final ControllerFactory factory = new ControllerFactoryImpl(builder, useCaseFactory);
    private Map<Integer, Object> args;

    @Before
    public void setUp() throws Exception {
        args = new HashMap<>();
    }

    @Test
    public void makeMethodReturnsRefreshController() {
        Controller controller = factory.make("RefreshController", args);
        assertTrue(controller instanceof RefreshController);
    }

    @Test
    public void makeMethodReturnsAddPersonController() {
        EntryEvent formEvent = new EntryEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");
        args.put(1, formEvent);
        Controller controller = factory.make("AddPersonController", args);
        assertTrue(controller instanceof AddPersonController);
    }

    @Test
    public void makeMethodReturnsDeletePersonController() {
        int idToDelete = 1;
        args.put(1, idToDelete);
        Controller controller = factory.make("DeletePersonController", args);
        assertTrue(controller instanceof DeletePersonController);
    }

    @Test
    public void makeMethodReturnsExportController() {
        File file = new File("Export.per");
        args.put(1, file);
        Controller controller = factory.make("ExportController", args);
        assertTrue(controller instanceof ExportController);
    }

    @Test
    public void makeMethodReturnsImportController() {
        File file = new File("Import.per");
        args.put(1, file);
        Controller controller = factory.make("ImportController", args);
        assertTrue(controller instanceof ImportController);
    }

    private class MainFrameDummy implements MainFramePresenter {
        @Override
        public void update(PersonTableModelRecord[] responses) {

        }
    }
}