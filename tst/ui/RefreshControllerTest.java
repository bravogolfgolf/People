package ui;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.*;
import domain.refresh.RefreshRequest;
import main.RequestBuilderImpl;
import main.ResponseBuilderImpl;
import main.UseCaseFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshControllerTest implements InputBoundary, View {

    private RefreshRequest r;

    @Override
    public void execute(Request request) {
        this.r = (RefreshRequest) request;
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
    private final PersonRepository repository = new PersonRepositoryInMemory();

    @Before
    public void setUp() {
        Person person = new Person(2, "New Full Name",
                "New Occupation", 0, 1,
                false, "New Tax ID", "Female");
        repository.addPerson(person);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new RefreshControllerTest.UseCaseFactoryImplStub();
        Controller controller = new RefreshController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertTrue(r != null);
    }

    @Test
    public void shouldReturnRecords() {
        ExportImport exportImport = new ExportImport();
        ResponseBuilder responseBuilder = new ResponseBuilderImpl();
        UseCaseFactory factory = new UseCaseFactoryImpl(repository, exportImport, responseBuilder, presenter);
        Controller controller = new RefreshController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(1, records.length);
    }

    private class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public InputBoundary make(String useCase) {
            return RefreshControllerTest.this;
        }
    }
}

