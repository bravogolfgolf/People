package controller;

import builderfactory.*;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import presenter.Presenter;
import presenter.View;
import usecase.AddPersonRequest;
import usecase.AddPersonUseCase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonControllerTest {
    private final Map<String, Class> requests = new HashMap<String, Class>() {{
        put("AddPerson", AddPersonRequest.class);
    }};
    private final RequestBuilder requestBuilder = new RequestBuilder(requests);
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = null;
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
        UseCaseFactoryDummy(Map<String, Class> useCases, Map<String, Class[]> constructorClasses, Map<String, Object[]> constructorObjects) {
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
