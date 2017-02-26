package contoller;

import org.junit.Before;
import org.junit.Test;
import other.Controller;
import requestor.InputBoundary;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.PersonRecord;
import responder.Presenter;
import usecase.RequestBuilderImpl;
import usecase.importfile.ImportRequest;
import view.PersonTablePanelPresenter;
import view.View;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ImportControllerTest implements InputBoundary, View {

    private ImportRequest r;

    @Override
    public void execute(Request request) {
        this.r = (ImportRequest) request;
    }

    @Override
    public String generateView(PersonRecord[] records) {
        return null;
    }

    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = this;

    private final File file = new File("ImportTest.per");

    @Before
    public void setUp() throws Exception {
        args.put(1, file);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = (useCase, presenter) -> ImportControllerTest.this;
        Controller controller = new ImportController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(file, r.file);
    }
}


