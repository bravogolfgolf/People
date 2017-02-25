package contoller;

import database.PersonRepository;
import database.PersonRepositoryInMemory;
import requestor.InputBoundary;
import requestor.Request;
import respondor.Presenter;
import usecase.addperson.AddPersonRequest;
import usecase.RequestBuilderImpl;
import usecase.UseCaseFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import view.*;

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
        UseCaseFactory factory = (useCase, presenter) -> AddPersonControllerTest.this;
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

    @Test
    public void shouldReturnRecords() {
        PersonRepository repository = new PersonRepositoryInMemory();
        UseCaseFactory factory = new UseCaseFactoryImpl(repository);
        Controller controller = new AddPersonController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(1, records.length);
    }
}
