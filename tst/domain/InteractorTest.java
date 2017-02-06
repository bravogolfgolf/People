package domain;

import data.Persistent;
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
    public void presentPeople(Map<Integer, Person> result) {
        this.result = result;
    }

    private final PersonRepository repository = new PersonRepository();
    private final PersistentInteractor persistent = new Persistent();
    private final PresenterInteractor presenter = this;
    private final Interactor interactor = new Interactor();
    private final PersonMessage request = new PersonMessage();
    private File file;

    @Before
    public void setUp() throws Exception {
        Person.setCounter(0);
        interactor.setRepository(repository);
        interactor.setPersistent(persistent);
        interactor.setPresenter(presenter);
    }

    @Test
    public void shouldProcessAddPersonRequestIntoAddPersonResult() {
        createRequest("Add Person");
        addEntryToRepository();

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
        makeSureTestFileDoesNotAlreadyExist("ExportTest.per");
        createRequest("Export");
        addEntryToRepository();
        try {
            exportRepository();
        } finally {
            deleteFile();
        }
    }

    private void exportRepository() throws IOException {
        interactor.exportRepository(file);
        assertTrue(file.exists());
    }

    private void makeSureTestFileDoesNotAlreadyExist(String pathname) {
        file = new File(pathname);
        assertTrue(!file.exists());
    }

    private void addEntryToRepository() {
        interactor.addPerson(request);
    }

    private void deleteFile() {
        assertTrue(file.delete());
    }

    @Test
    public void shouldImportPersonRepositoryFromFileAndResetPersonCounter() throws IOException {
        final int SAME_KEY_BEFORE_AFTER_IMPORT_RETURN_PROPER_RESULT = 1;
        makeSureTestFileDoesNotAlreadyExist(("ImportTest.per"));

        createRequest("Import0");
        addEntryToRepository();
        assertEquals(1, result.size());

        exportRepository();

        createRequest("Import1");
        addEntryToRepository();
        assertEquals(2, result.size());
        assertEquals("Import1", result.get(SAME_KEY_BEFORE_AFTER_IMPORT_RETURN_PROPER_RESULT).getFullName());

        interactor.loadRepository(file);
        assertEquals(1, result.size());

        for (Person expected : result.values()) {
            assertEquals("Import0", expected.getFullName());
            assertEquals(request.occupation, expected.getOccupation());
            assertEquals(request.ageCategory, expected.getAgeCategory());
            assertEquals(request.employmentStatus, expected.getEmploymentStatus());
            assertTrue(expected.isUsCitizen());
            assertEquals(request.taxId, expected.getTaxId());
            assertEquals(request.gender, expected.getGender());
        }

        createRequest("Import2");
        addEntryToRepository();
        assertEquals(2, result.size());
        assertEquals("Import2", result.get(SAME_KEY_BEFORE_AFTER_IMPORT_RETURN_PROPER_RESULT).getFullName());

        mapKeysMatchPersonIDs();

        deleteFile();
    }

    private void mapKeysMatchPersonIDs() {
        for (Integer key : result.keySet())
            assertTrue(key.equals(result.get(key).getId()));
    }

    @Test
    public void shouldDeletePersonWithId() {
        int id = 0;
        createRequest("Person0");
        addEntryToRepository();
        createRequest("Person1");
        addEntryToRepository();
        assertEquals(2, this.result.size());
        result = null;
        interactor.deletePerson(id);
        assertEquals(1, this.result.size());
    }

}
