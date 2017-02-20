package domain;

import data.PersonRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImportUseCaseTest implements PresenterInteractor {

    private Map<Integer, Person> result;

    @Override
    public void presentPeople(Map<Integer, Person> result) {
        this.result = result;
    }

    private final ExportImport exportImport = new ExportImport();
    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final UseCase useCase = new ImportUseCase(exportImport, repository, this);
    private final ImportRequest request = new ImportRequest();
    private final File file = new File(("ImportTest.per"));

    @Before
    public void setUp() {
        request.file = file;
    }

    @Test
    public void shouldImportPersonRepositoryFromFile() {
        assertTrue(file.exists());
        useCase.execute(request);
        assertEquals(1, result.size());

        for (Person expected : result.values()) {
            assertEquals("Import Test", expected.getFullName());
            assertEquals("Occupation", expected.getOccupation());
            assertEquals(1, expected.getAgeCategory());
            assertEquals(2, expected.getEmploymentStatus());
            assertTrue(expected.isUsCitizen());
            assertEquals("000-00-0000", expected.getTaxId());
            assertEquals("Male", expected.getGender());
        }
    }

    @Test(expected = ImportUseCase.ImportFailed.class)
    public void shouldThrowException() {
        request.file = new File("/bad_path/ImportTest.per");
        useCase.execute(request);
    }
}
