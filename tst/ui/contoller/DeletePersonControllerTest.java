package ui.contoller;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.*;
import domain.deleteperson.DeletePersonRequest;
import main.RequestBuilderImpl;
import main.UseCaseFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import ui.RequestBuilder;
import ui.View;
import ui.presenter.PersonTablePanelPresenter;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DeletePersonControllerTest implements InputBoundary, View {

    private DeletePersonRequest r;

    @Override
    public void execute(Request request) {
        this.r = (DeletePersonRequest) request;
    }

    private PersonRecord[] records;

    @Override
    public String generateView(PersonRecord[] records) {
        this.records = records;
        return null;
    }

    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = this;
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final int idToDelete = 1;

    @Before
    public void setUp() throws Exception {
        Person person1 = new Person(1, "New Full Name1",
                "New Occupation1", 1, 1,
                false, "New Tax ID1", "Male");

        Person person2 = new Person(2, "New Full Name2",
                "New Occupation1", 2, 2,
                true, "New Tax ID2", "Female");
        repository.addPerson(person1);
        repository.addPerson(person2);
        args.put(1, idToDelete);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = (useCase, presenter) -> DeletePersonControllerTest.this;
        Controller controller = new DeletePersonController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(idToDelete, r.id);
    }

    @Test
    public void shouldReturnRecords() {
        UseCaseFactory factory = new UseCaseFactoryImpl(repository);
        Controller controller = new DeletePersonController(requestBuilder, args, factory, presenter, view);
        assertEquals(2, repository.getPeople().size());

        controller.execute();

        assertEquals(1, records.length);
    }
}


