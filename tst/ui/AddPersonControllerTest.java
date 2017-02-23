package ui;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.*;
import domain.addperson.AddPersonRequest;
import main.RequestBuilderImpl;
import main.ResponseBuilderImpl;
import main.UseCaseFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import ui.contoller.AddPersonController;
import ui.contoller.Controller;
import ui.contoller.UseCaseFactory;

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

    private PersonTableModelRecord[] records;

    @Override
    public Object generateView(PersonTableModelRecord[] records) {
        this.records = records;
        return null;
    }

    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final PersonTablePanelPresenter presenter = new PersonTablePanelPresenter();
    private final View view = this;
    private final EntryEvent entryEvent = new EntryEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");


    @Before
    public void setUp() throws Exception {
        args.put(1, entryEvent);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryImplStub();
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
        ExportImport exportImport = new ExportImport();
        ResponseBuilder responseBuilder = new ResponseBuilderImpl();
        UseCaseFactory factory = new UseCaseFactoryImpl(repository, responseBuilder);
        Controller controller = new AddPersonController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(1, records.length);
    }

    class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public InputBoundary make(String useCase, Presenter presenter) {
            return AddPersonControllerTest.this;
        }
    }
}

