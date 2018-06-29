package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import responder.ImportResponder;
import responder.Responder;
import usecase.ImportRequest;
import usecase.ImportUseCase;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImportControllerTest implements Responder {
    private boolean responderGenerateViewCalled = false;

    @Override
    public Object generateView() {
        responderGenerateViewCalled = true;
        return null;
    }

    private final ImportRequest request = new ImportRequest();
    private final UseCase useCase = new ImportUseCaseSpy(null, null);
    private final Responder responder = this;
    private final Controller controller = new ImportController(request, useCase, responder);
    private final File file = new File("ImportTest.per");

    private ImportRequest r;

    @Before
    public void setUp() {
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



