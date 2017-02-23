package ui;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.*;
import domain.refresh.RefreshRequest;
import main.RequestBuilderImpl;
import main.UseCaseFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import ui.contoller.Controller;
import ui.contoller.RefreshController;
import ui.contoller.UseCaseFactory;

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

    private PersonRecord[] records;

    @Override
    public PersonTableModelRecord[] generateView(PersonRecord[] records) {
        this.records = records;
        return null;
    }

    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
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
        UseCaseFactory factory = (useCase, presenter) -> RefreshControllerTest.this;
        Controller controller = new RefreshController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertTrue(r != null);
    }

    @Test
    public void shouldReturnRecords() {
        UseCaseFactory factory = new UseCaseFactoryImpl(repository);
        Controller controller = new RefreshController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(1, records.length);
    }
}

