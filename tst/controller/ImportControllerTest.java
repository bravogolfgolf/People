package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import responder.ImportResponder;
import responder.ImportResponse;
import responder.View;
import ui_swing.ImportView;
import usecase.ImportRequest;
import usecase.ImportUseCase;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImportControllerTest implements ImportResponder {
    private boolean responderGenerateViewCalled = false;

    @Override
    public void present(ImportResponse response) {

    }

    @Override
    public Object generateView() {
        responderGenerateViewCalled = true;
        return null;
    }

    private final ImportRequest request = new ImportRequest();
    private final UseCase useCase = new ImportUseCaseSpy(null, null);
    private final View view = new ImportView();
    private final ImportResponder responder = this;
    private final Controller controller = new ImportController(request, useCase, responder, view);
    private final File file = new File("ImportTest.per");

    private ImportRequest r;

    @Before
    public void setUp() throws Exception {
        request.file = file;
    }

    @Test
    public void shouldSendRequestToUseCase() {
        controller.execute();

        assertEquals(file, r.file);
    }

    @Test
    public void shouldCallResponder() {
        controller.execute();

        assertTrue(responderGenerateViewCalled);
    }

    private class ImportUseCaseSpy extends ImportUseCase {
        ImportUseCaseSpy(ExportImport importer, ImportResponder responder) {
            super(importer, responder);
        }

        public void execute(Request request) {
            r = (ImportRequest) request;
        }
    }
}



