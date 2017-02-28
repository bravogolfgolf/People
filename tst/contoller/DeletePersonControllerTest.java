package contoller;

import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
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
import usecase.deleteperson.DeletePersonRequest;
import usecase.deleteperson.DeletePersonUseCase;
import view.PersonTablePanelPresenter;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DeletePersonControllerTest {
    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = null;
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final int idToDelete = 1;
    private DeletePersonRequest r;

    @Before
    public void setUp() throws Exception {
        repository.addPerson("New Full Name1", "New Occupation1", 1, 1, false, "New Tax ID1", "Male");
        repository.addPerson("New Full Name2", "New Occupation1", 2, 2, true, "New Tax ID2", "Female");
        args.put(1, idToDelete);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);
        Controller controller = new DeletePersonController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(idToDelete, r.id);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class<? extends UseCase>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Presenter presenter) {
            return new DeletePersonUseCaseSpy(null, null);
        }
    }

    private class DeletePersonUseCaseSpy extends DeletePersonUseCase {
        DeletePersonUseCaseSpy(PersonRepository repository, Presenter presenter) {
            super(repository, presenter);
        }

        public void execute(Request request) {
            r = (DeletePersonRequest) request;
        }
    }
}


