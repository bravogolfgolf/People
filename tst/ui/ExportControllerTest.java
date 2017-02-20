package ui;

import domain.ExportRequest;
import main.RequestBuilderImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExportControllerTest implements UseCase {

    private ExportRequest r;

    @Override
    public void execute(Request request) {
        this.r = (ExportRequest) request;
    }

    private final RequestBuilder builder = new RequestBuilderImpl();
    private final UseCaseFactory factory = new UseCaseFactoryImplStub();
    private final Map<Integer, Object> args = new HashMap<>();
    private final String file = "Export.per";

    @Before
    public void setUp() throws Exception {
        args.put(1, file);
    }

    @Test
    public void shouldSendRequestToUseCase()  {
        Controller controller = new ExportController(builder, args, factory);

        controller.execute();

        assertEquals(file, r.file);

    }

    class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public UseCase make(String useCase) {
            return ExportControllerTest.this;
        }
    }
}


