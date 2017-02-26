package contoller;

import exportimportgateway.Export;
import org.junit.Before;
import org.junit.Test;
import other.Controller;
import other.View;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.PersonRecord;
import responder.Presenter;
import responder.Response;
import usecase.RequestBuilderImpl;
import usecase.exportfile.ExportRequest;
import usecase.exportfile.ExportUseCase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExportControllerTest {
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
    private ExportRequest r;

    @Before
    public void setUp() throws Exception {
        args.put(1, file);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new ExportController(builder, args, factory, presenter, view);

        controller.execute();

        assertEquals(file, r.file);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class<? extends UseCase>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Presenter presenter) {
            return new ExportUseCaseSpy(null, null);
        }
    }

    private class ExportUseCaseSpy extends ExportUseCase {
        ExportUseCaseSpy(Export exporter, Presenter presenter) {
            super(exporter, presenter);
        }

        public void execute(Request request) {
            r = (ExportRequest) request;
        }
    }
}


