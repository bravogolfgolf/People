package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import responder.ExportResponder;
import responder.View;
import ui_swing.ExportPresenter;
import ui_swing.ExportView;
import usecase.ExportRequest;
import usecase.ExportUseCase;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ExportControllerTest {
    private final ExportRequest request = new ExportRequest();
    private final UseCase useCase = new ExportUseCaseSpy(null, null);
    private final ExportResponder responder = new ExportPresenter();
    private final View view = new ExportView();
    private final File file = new File("Export.per");
    private ExportRequest r;

    @Before
    public void setUp() throws Exception {
        request.file = file;
    }

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new ExportController(request, useCase, responder, view);

        controller.execute();

        assertEquals(file, r.file);
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


