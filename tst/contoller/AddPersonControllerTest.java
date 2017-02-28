package contoller;

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
import usecase.addperson.AddPersonRequest;
import usecase.addperson.AddPersonUseCase;
import view.EntryEvent;
import view.PersonTablePanelPresenter;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonControllerTest {
    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = null;
    private final EntryEvent entryEvent = new EntryEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");
    private AddPersonRequest r;

    @Before
    public void setUp() throws Exception {
        args.put(1, entryEvent);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);
        Controller controller = new AddPersonController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(entryEvent.getFullName(), r.fullName);
        assertEquals(entryEvent.getOccupation(), r.occupation);
        assertEquals(entryEvent.getAgeCategory(), r.ageCategory);
        assertEquals(entryEvent.getEmploymentStatus(), r.employmentStatus);
        assertTrue(r.uSCitizen);
        assertEquals(entryEvent.getTaxId(), r.taxId);
        assertEquals(entryEvent.getGender(), r.gender);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class<? extends UseCase>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
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

