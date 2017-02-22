package ui;

import domain.addperson.AddPersonRequest;
import domain.Request;
import domain.InputBoundary;
import main.RequestBuilderImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonControllerTest implements InputBoundary {

    private AddPersonRequest r;

    @Override
    public void execute(Request request) {
        this.r = (AddPersonRequest) request;
    }

    private final RequestBuilder builder = new RequestBuilderImpl();
    private final UseCaseFactory factory = new UseCaseFactoryImplStub();
    private final Map<Integer, Object> args = new HashMap<>();
    private final EntryEvent entryEvent = new EntryEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");

    @Before
    public void setUp() throws Exception {
        args.put(1, entryEvent);
    }

    @Test
    public void shouldSendRequestToUseCase()  {
        Controller controller = new AddPersonController(builder, args, factory);

        controller.execute();

        assertEquals(entryEvent.getFullName(), r.fullName);
        assertEquals(entryEvent.getOccupation(), r.occupation);
        assertEquals(entryEvent.getAgeCategory(), r.ageCategory);
        assertEquals(entryEvent.getEmploymentStatus(), r.employmentStatus);
        assertTrue(r.uSCitizen);
        assertEquals(entryEvent.getTaxId(), r.taxId);
        assertEquals(entryEvent.getGender(), r.gender);
    }

    class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public InputBoundary make(String useCase) {
            return AddPersonControllerTest.this;
        }
    }
}

