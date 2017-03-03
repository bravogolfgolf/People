package controller;

import builderfactory.*;
import gateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import response.Presenter;
import usecase.ImportRequest;
import usecase.ImportUseCase;
import response.View;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ImportControllerTest {
    private final Map<String, Class> requests = new HashMap<String, Class>() {{
        put("Import", ImportRequest.class);
    }};
    private final RequestBuilder requestBuilder = new RequestBuilder(requests);
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = null;
    private final View view = null;
    private final File file = new File("ImportTest.per");
    private ImportRequest r;

    @Before
    public void setUp() throws Exception {
        args.put(0, file);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);

        Controller controller = new ImportController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(file, r.file);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class> useCases, Map<String, Class[]> constructorClasses, Map<String, Object[]> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Presenter presenter) {
            return new ImportUseCaseSpy(null, null);
        }
    }

    private class ImportUseCaseSpy extends ImportUseCase {
        ImportUseCaseSpy(ExportImport importer, Presenter presenter) {
            super(importer, presenter);
        }

        public void execute(Request request) {
            r = (ImportRequest) request;
        }
    }
}



