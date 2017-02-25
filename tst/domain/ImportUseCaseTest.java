package domain;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.importfile.Import;
import domain.importfile.ImportRequest;
import domain.importfile.ImportUseCase;
import entity.PersonTemplate;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImportUseCaseTest {
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final Import exportImport = new ExportImport(repository);
    private final InputBoundary refreshUseCase = request -> {
    };
    private final InputBoundary useCase = new ImportUseCase(exportImport, repository, refreshUseCase);
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
        assertEquals(1, repository.getPeople().size());

        for (PersonTemplate expected : repository.getPeople().values()) {
            assertEquals(1, expected.getId());
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
