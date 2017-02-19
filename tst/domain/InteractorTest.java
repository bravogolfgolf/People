package domain;

import data.PersonRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InteractorTest implements PresenterInteractor {

    private Map<Integer, Person> result;

    @Override
    public void presentPeople(Map<Integer, Person> result) {
        this.result = result;
    }

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final ExportImport persistent = new ExportImport();
    private final PresenterInteractor presenter = this;
    private final Interactor interactor = new Interactor();
    private final AddPersonRequest request = new AddPersonRequest();
    private File file;

    @Before
    public void setUp() throws Exception {
        interactor.setRepository(repository);
        interactor.setPersistent(persistent);
        interactor.setPresenter(presenter);
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
    public void shouldImportPersonRepositoryFromFileAndResetPersonCounter() throws IOException, ClassNotFoundException, SQLException {
        final int SAME_KEY_BEFORE_AFTER_IMPORT_RETURN_PROPER_RESULT = 1;
        makeSureTestFileDoesNotAlreadyExist(("../ImportTest.per"));

        createRequest("Import1");
        addEntryToRepository();
        assertEquals(1, result.size());

        exportRepository();

        createRequest("Import2");
        addEntryToRepository();
        assertEquals(2, result.size());
        assertEquals("Import1", result.get(SAME_KEY_BEFORE_AFTER_IMPORT_RETURN_PROPER_RESULT).getFullName());

        interactor.loadRepository(file);
        assertEquals(1, result.size());

        for (Person expected : result.values()) {
            assertEquals("Import1", expected.getFullName());
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
        assertEquals("Import1", result.get(SAME_KEY_BEFORE_AFTER_IMPORT_RETURN_PROPER_RESULT).getFullName());

        mapKeysMatchPersonIDs();

        deleteFile();
    }

    private void mapKeysMatchPersonIDs() {
        for (Integer key : result.keySet())
            assertTrue(key.equals(result.get(key).getId()));
    }

    @Test
    public void shouldDeletePersonWithId() throws SQLException, ClassNotFoundException {
        int id = 1;
        createRequest("Person1");
        addEntryToRepository();
        createRequest("Person2");
        addEntryToRepository();
        assertEquals(2, this.result.size());
        result = null;
        interactor.deletePerson(id);
        assertEquals(1, this.result.size());
    }

}
