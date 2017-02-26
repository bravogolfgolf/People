package contoller;

import database.PersonRepository;
import database.PersonRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;
import requestor.InputBoundary;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.PersonRecord;
import responder.Presenter;
import usecase.RequestBuilderImpl;
import usecase.deleteperson.DeletePersonRequest;
import view.PersonTablePanelPresenter;
import view.View;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DeletePersonControllerTest implements InputBoundary, View {

    private DeletePersonRequest r;

    @Override
    public void execute(Request request) {
        this.r = (DeletePersonRequest) request;
    }

    @Override
    public String generateView(PersonRecord[] records) {
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
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);
        DeletePersonController controller = new DeletePersonController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(idToDelete, r.id);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class<? extends InputBoundary>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public InputBoundary make(String useCase, Presenter presenter) {
            return DeletePersonControllerTest.this;
        }
    }
}


