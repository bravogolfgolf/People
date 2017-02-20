package ui;

import domain.ImportRequest;
import domain.Request;
import domain.UseCase;
import main.RequestBuilderImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ImportControllerTest implements UseCase {

    private ImportRequest r;

    @Override
    public void execute(Request request) {
        this.r = (ImportRequest) request;
    }

    private final RequestBuilder builder = new RequestBuilderImpl();
    private final UseCaseFactory factory = new UseCaseFactoryImplStub();
    private final Map<Integer, Object> args = new HashMap<>();
    private final File file = new File("Import.per");

    @Before
    public void setUp() throws Exception {
        args.put(1, file);
    }

    @Test
    public void shouldSendRequestToUseCase()  {
        Controller controller = new ImportController(builder, args, factory);

        controller.execute();

        assertEquals(file, r.file);

    }

    class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public UseCase make(String useCase) {
            return ImportControllerTest.this;
        }
    }
}


