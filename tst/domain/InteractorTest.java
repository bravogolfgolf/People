package domain;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class InteractorTest implements PresenterInteractor {

    private Map<Integer, Person> result;

    @Override
    public void addPerson(Map<Integer, Person> result) {
        this.result = result;
    }

    private final PersonRepository repository = new PersonRepository();
    private final PresenterInteractor presenter = this;
    private final Interactor interactor = new Interactor();
    private final PersonMessage request = new PersonMessage();
    private File file;

    @Before
    public void setUp() throws Exception {
        interactor.setRepository(repository);
        interactor.setPresenter(presenter);
    }

    @Test
    public void shouldProcessAddPersonRequestIntoAddPersonResult() {
        createRequest("Add Person");
        interactor.addPerson(request);

        for (Person expected : result.values()) {
            assertEquals(request.fullName, expected.getFullName());
            assertEquals(request.occupation, expected.getOccupation());
            assertEquals(request.ageCategory, expected.getAgeCategory());
            assertEquals(request.employmentStatus, expected.getEmploymentStatus());
            assertTrue(expected.isUsCitizen());
            assertEquals(request.taxId, expected.getTaxId());
            assertEquals(request.gender, expected.getGender());
        }
    }

    private void createRequest(String fullName) {
        request.fullName = fullName;
        request.occupation = "Occupation";
        request.ageCategory = 0;
        request.employmentStatus = 0;
        request.uSCitizen = true;
        request.taxId = "000-00-0000";
        request.gender = "Male";
    }

    @Test
    public void shouldExportPersonRepositoryToFile() throws IOException {
        createRequest("Export");
        file = new File(("ExportTest.per"));
        assertTrue(!deleteFile());
        try {
            interactor.addPerson(request);
            interactor.exportRepository(file);
            assertTrue(file.exists());
        } finally {
            assertTrue(deleteFile());
        }
    }


    private boolean deleteFile() {
        return file.delete();
    }

    @Test
    public void shouldImportPersonRepositoryFromFile() throws IOException {
        createRequest("Import");
        file = new File(("ImportTest.per"));
        assertTrue(!deleteFile());
        interactor.addPerson(request);
        result = null;
        interactor.exportRepository(file);
        assertTrue(file.exists());

        try {
            interactor.loadRepository(file);
            for (Person expected : result.values()) {
                assertEquals(request.fullName, expected.getFullName());
                assertEquals(request.occupation, expected.getOccupation());
                assertEquals(request.ageCategory, expected.getAgeCategory());
                assertEquals(request.employmentStatus, expected.getEmploymentStatus());
                assertTrue(expected.isUsCitizen());
                assertEquals(request.taxId, expected.getTaxId());
                assertEquals(request.gender, expected.getGender());
            }
        } finally {
            assertTrue(deleteFile());
        }
    }
}