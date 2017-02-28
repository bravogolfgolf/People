package contoller;

import exportimportgateway.ExportImport;
import org.junit.Before;
import org.junit.Test;
import other.Controller;
import other.View;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Presenter;
import usecase.RequestBuilderImpl;
import usecase.importfile.ImportRequest;
import usecase.importfile.ImportUseCase;
import view.PersonTablePanelPresenter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ImportControllerTest {
    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = null;
    private final File file = new File("ImportTest.per");
    private ImportRequest r;

    @Before
    public void setUp() throws Exception {
        args.put(1, file);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);

        Controller controller = new ImportController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(file, r.file);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class<? extends UseCase>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
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



