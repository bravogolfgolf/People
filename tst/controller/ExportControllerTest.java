package controller;

import builderfactory.*;
import gateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import responder.ExportResponder;
import responder.ExportResponse;
import responder.View;
import usecase.ExportRequest;
import usecase.ExportUseCase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExportControllerTest {
    private final Map<String, Class> requests = new HashMap<String, Class>() {{
        put("Export", ExportRequest.class);
    }};
    private final RequestBuilder builder = new RequestBuilder(requests);
    private final Map<Integer, Object> args = new HashMap<>();
    private final UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);
    private final ExportResponder presenter = new ExportResponder() {
        @Override
        public void present(ExportResponse response) {

        }

        @Override
        public int getViewModel() {
            return 0;
        }
    };
    private final View view = object -> null;
    private final File file = new File("Export.per");
    private ExportRequest r;

    @Before
    public void setUp() throws Exception {
        args.put(0, file);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new ExportController(builder, args, factory, presenter, view);

        controller.execute();

        assertEquals(file, r.file);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class> useCases, Map<String, Class[]> constructorClasses, Map<String, Object[]> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Object responder) {
            return new ExportUseCaseSpy(null, null);
        }
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


