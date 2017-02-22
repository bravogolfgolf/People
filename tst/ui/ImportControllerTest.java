package ui;

import domain.InputBoundary;
import domain.Request;
import domain.importfile.ImportRequest;
import main.RequestBuilderImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ImportControllerTest implements InputBoundary {

    private ImportRequest r;

    @Override
    public void execute(Request request) {
        this.r = (ImportRequest) request;
    }

    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final UseCaseFactory factory = new UseCaseFactoryImplStub();
    private final Map<Integer, Object> args = new HashMap<>();
    private final File file = new File("ImportTest.per");

    @Before
    public void setUp() throws Exception {
        args.put(1, file);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new ImportController(requestBuilder, args, factory);

        controller.execute();

        assertEquals(file, r.file);
    }

    class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public InputBoundary make(String useCase) {
            return ImportControllerTest.this;
        }
    }
}


