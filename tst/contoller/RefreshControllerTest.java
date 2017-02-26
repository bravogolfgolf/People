package contoller;

import database.PersonRepository;
import database.PersonRepositoryInMemory;
import databasegateway.AddPersonGateway;
import databasegateway.DeletePersonGateway;
import databasegateway.RefreshGateway;
import exportimport.ExportImport;
import exportimportgateway.Export;
import exportimportgateway.Import;
import org.junit.Before;
import org.junit.Test;
import other.Controller;
import other.View;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.PersonRecord;
import responder.Presenter;
import usecase.RequestBuilderImpl;
import usecase.addperson.AddPersonUseCase;
import usecase.deleteperson.DeletePersonUseCase;
import usecase.exportfile.ExportUseCase;
import usecase.importfile.ImportUseCase;
import usecase.refresh.RefreshRequest;
import usecase.refresh.RefreshUseCase;
import view.PersonTableModelRecord;
import view.PersonTablePanelPresenter;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshControllerTest implements View {

    private PersonRecord[] records;

    @Override
    public PersonTableModelRecord[] generateView(PersonRecord[] records) {
        this.records = records;
        return null;
    }

    private final RequestBuilder requestBuilder = new RequestBuilderImpl();
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = this;
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new ExportImport(repository);
    private final Map<String, Class<? extends UseCase>> useCases = new HashMap<>();
    private final Map<String, Class<?>[]> constructorClasses = new HashMap<>();
    private final Map<String, Object> constructorObjects = new HashMap<>();
    private RefreshRequest r;

    @Before
    public void setUp() {
        repository.addPerson("New Full Name", "New Occupation", 0, 1, false, "New Tax ID", "Female");
        setUseCases();
        setConstructorClasses();
        setConstructorObjects();
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);

        Controller controller = new RefreshController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertTrue(r != null);
    }

    @Test
    public void shouldReturnRecords() {
        UseCaseFactory factory = new UseCaseFactory(useCases, constructorClasses, constructorObjects);
        Controller controller = new RefreshController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(1, records.length);
    }

    private void setUseCases() {
        useCases.put("RefreshUseCase", RefreshUseCase.class);
        useCases.put("AddPersonUseCase", AddPersonUseCase.class);
        useCases.put("DeletePersonUseCase", DeletePersonUseCase.class);
        useCases.put("ExportUseCase", ExportUseCase.class);
        useCases.put("ImportUseCase", ImportUseCase.class);
    }

    private void setConstructorClasses() {
        constructorClasses.put("RefreshUseCase", new Class[]{RefreshGateway.class, Presenter.class});
        constructorClasses.put("AddPersonUseCase", new Class[]{AddPersonGateway.class, Presenter.class});
        constructorClasses.put("DeletePersonUseCase", new Class[]{DeletePersonGateway.class, Presenter.class});
        constructorClasses.put("ExportUseCase", new Class[]{Export.class, Presenter.class});
        constructorClasses.put("ImportUseCase", new Class[]{Import.class, Presenter.class});
    }

    private void setConstructorObjects() {
        constructorObjects.put("RefreshUseCase", repository);
        constructorObjects.put("AddPersonUseCase", repository);
        constructorObjects.put("DeletePersonUseCase", repository);
        constructorObjects.put("ExportUseCase", exportImport);
        constructorObjects.put("ImportUseCase", exportImport);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {

        UseCaseFactoryDummy(Map<String, Class<? extends UseCase>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Presenter presenter) {
            return new RefreshUseCaseSpy(null, null);
        }
    }

    private class RefreshUseCaseSpy extends RefreshUseCase {
        RefreshUseCaseSpy(RefreshGateway repository, Presenter presenter) {
            super(repository, presenter);
        }

        public void execute(Request request) {
            r = (RefreshRequest) request;
        }
    }
}

