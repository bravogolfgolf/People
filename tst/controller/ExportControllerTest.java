package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import responder.ExportResponder;
import responder.Responder;
import usecase.ExportRequest;
import usecase.ExportUseCase;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExportControllerTest implements Responder {
    private boolean responderGenerateViewCalled = false;

    @Override
    public Object generateView() {
        responderGenerateViewCalled = true;
        return null;
    }

    private final ExportRequest request = new ExportRequest();
    private final UseCase useCase = new ExportUseCaseSpy(null, null);
    private final Responder responder = this;
    private final Controller controller = new ExportController(request, useCase, responder);
    private final File file = new File("Export.per");

    private ExportRequest r;

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

    private class ExportUseCaseSpy extends ExportUseCase {
        ExportUseCaseSpy(ExportImport exporter, ExportResponder responder) {
            super(exporter, responder);
        }

        public void execute(Request request) {
            r = (ExportRequest) request;
        }
    }
}


