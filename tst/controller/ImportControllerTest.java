package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import responder.ImportResponder;
import responder.View;
import ui_swing.ImportPresenter;
import ui_swing.ImportView;
import usecase.ImportRequest;
import usecase.ImportUseCase;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ImportControllerTest {
    private final ImportRequest request = new ImportRequest();
    private final UseCase useCase = new ImportUseCaseSpy(null, null);
    private final ImportResponder responder = new ImportPresenter();
    private final View view = new ImportView();
    private final File file = new File("ImportTest.per");
    private ImportRequest r;

    @Before
    public void setUp() throws Exception {
        request.file = file;
    }

    @Test
    public void shouldSendRequestToUseCase() {

        Controller controller = new ImportController(request, useCase, responder, view);

        controller.execute();

        assertEquals(file, r.file);
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



