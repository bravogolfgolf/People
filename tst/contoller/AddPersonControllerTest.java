package contoller;

import contollerfactory.Controller;
import databasegateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Presenter;
import view.View;
import usecase.addperson.AddPersonRequest;
import usecase.addperson.AddPersonUseCase;
import ui.PersonTablePanelPresenter;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonControllerTest {
    private final Map<String, Class<? extends Request>> requests = new HashMap<String, Class<? extends Request>>() {{
        put("AddPersonRequest", AddPersonRequest.class);
    }};
    private final RequestBuilder requestBuilder = new RequestBuilder(requests);
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = null;
    private AddPersonRequest r;

    @Before
    public void setUp() throws Exception {
        args.put(0, "Full Name");
        args.put(1, "Occupation");
        args.put(2, 0);
        args.put(3, 0);
        args.put(4, true);
        args.put(5, "Tax ID");
        args.put(6, "Gender");
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);
        Controller controller = new AddPersonController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(args.get(0), r.fullName);
        assertEquals(args.get(1), r.occupation);
        assertEquals(args.get(2), r.ageCategory);
        assertEquals(args.get(3), r.employmentStatus);
        assertTrue(r.uSCitizen);
        assertEquals(args.get(5), r.taxId);
        assertEquals(args.get(6), r.gender);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class<? extends UseCase>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object[]> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Presenter presenter) {
            return new AddPersonUseCaseSpy(null, null);
        }
    }

    private class AddPersonUseCaseSpy extends AddPersonUseCase {
        AddPersonUseCaseSpy(PersonRepository repository, Presenter presenter) {
            super(repository, presenter);
        }

        public void execute(Request request) {
            r = (AddPersonRequest) request;
        }
    }
}

