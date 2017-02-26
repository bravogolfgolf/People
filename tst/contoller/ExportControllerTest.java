package contoller;

import org.junit.Before;
import org.junit.Test;
import requestor.InputBoundary;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.PersonRecord;
import responder.Presenter;
import responder.Response;
import usecase.RequestBuilderImpl;
import usecase.exportfile.ExportRequest;
import view.View;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExportControllerTest implements InputBoundary {

    private ExportRequest r;

    @Override
    public void execute(Request request) {
        this.r = (ExportRequest) request;
    }

    private final RequestBuilder builder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);

    private final Presenter presenter = new Presenter() {
        @Override
        public void present(Response response) {

        }

        @Override
        public PersonRecord[] getViewModel() {
            return null;
        }
    };
    private final View view = records -> null;
    private final File file = new File("Export.per");

    @Before
    public void setUp() throws Exception {
        args.put(1, file);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        ExportController controller = new ExportController(builder, args, factory, presenter, view);

        controller.execute();

        assertEquals(file, r.file);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class<? extends InputBoundary>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public InputBoundary make(String useCase, Presenter presenter) {
            return ExportControllerTest.this;
        }
    }
}


