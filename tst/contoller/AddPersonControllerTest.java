package contoller;

import org.junit.Before;
import org.junit.Test;
import requestor.InputBoundary;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.PersonRecord;
import responder.Presenter;
import usecase.RequestBuilderImpl;
import usecase.addperson.AddPersonRequest;
import view.EntryEvent;
import view.PersonTablePanelPresenter;
import other.View;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonControllerTest implements InputBoundary, View {

    private AddPersonRequest r;

    @Override
    public void execute(Request request) {
        this.r = (AddPersonRequest) request;
    }

    private PersonRecord[] records;

    @Override
    public Object generateView(PersonRecord[] records) {
        this.records = records;
        return null;
    }

    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = this;
    private final EntryEvent entryEvent = new EntryEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");

    @Before
    public void setUp() throws Exception {
        args.put(1, entryEvent);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);
        AddPersonController controller = new AddPersonController(requestBuilder, args, factory, presenter, view);

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
        UseCaseFactoryDummy(Map<String, Class<? extends InputBoundary>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public InputBoundary make(String useCase, Presenter presenter) {
            return AddPersonControllerTest.this;
        }
    }
}

