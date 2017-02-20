package main;

import org.junit.Before;
import org.junit.Test;
import ui.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ControllerFactoryImplTest {

    private final ControllerFactory factory = new ControllerFactoryImpl();
    private  Map<Integer, Object> args;

    @Before
    public void setUp() throws Exception {
        args = new HashMap<>();
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
        String file = "Export.per";
        args.put(1, file);
        Controller controller = factory.make("ExportController", args);
        assertTrue(controller instanceof ExportController);
    }

    @Test
    public void makeMethodReturnsImportController() {
        String file = "Import.per";
        args.put(1, file);
        Controller controller = factory.make("ImportController", args);
        assertTrue(controller instanceof ImportController);
    }
}