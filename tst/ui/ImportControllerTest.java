package ui;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.*;
import domain.importfile.ImportRequest;
import main.RequestBuilderImpl;
import main.ResponseBuilderImpl;
import main.UseCaseFactoryImpl;
import org.junit.Before;
import org.junit.Test;

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

    private PersonTableModelRecord[] records;

    @Override
    public void update(PersonTableModelRecord[] records) {
        this.records = records;
    }

    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final PersonTablePanelPresenter presenter = new PersonTablePanelPresenter();
    private final View view = this;

    private final File file = new File("ImportTest.per");

    @Before
    public void setUp() throws Exception {
        args.put(1, file);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryImplStub();
        Controller controller = new ImportController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(file, r.file);
    }

    @Test
    public void shouldReturnRecords() {
        PersonRepository repository = new PersonRepositoryInMemory();
        ExportImport exportImport = new ExportImport();
        ResponseBuilder responseBuilder = new ResponseBuilderImpl();
        UseCaseFactory factory = new UseCaseFactoryImpl(repository, exportImport, responseBuilder, presenter);
        Controller controller = new ImportController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(1, records.length);
    }

    class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public InputBoundary make(String useCase) {
            return ImportControllerTest.this;
        }
    }
}


