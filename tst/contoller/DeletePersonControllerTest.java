package contoller;

import database.PersonRepository;
import database.PersonRepositoryInMemory;
import other.Controller;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import requestor.InputBoundary;
import respondor.PersonRecord;
import respondor.Presenter;
import requestor.Request;
import usecase.deleteperson.DeletePersonRequest;
import usecase.RequestBuilderImpl;
import usecase.UseCaseFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import view.View;
import view.PersonTablePanelPresenter;

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
        repository.addPerson("New Full Name1", "New Occupation1", 1, 1, false, "New Tax ID1", "Male");
        repository.addPerson("New Full Name2", "New Occupation1", 2, 2, true, "New Tax ID2", "Female");
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


