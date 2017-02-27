package usecase;

import database.PersonRepositoryInMemory;
import databasegateway.ExportImportGateway;
import databasegateway.PersonRepository;
import entity.PersonTemplate;
import exportimport.ExportImport;
import exportimportgateway.Import;
import org.junit.Before;
import org.junit.Test;
import usecase.importfile.ImportRequest;
import usecase.importfile.ImportUseCase;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImportUseCaseTest {
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final Import exportImport = new ExportImport(repository);
    private final ImportUseCase useCase = new ImportUseCase(exportImport, null);
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
        assertEquals(1, repository.findAll().size());

        for (PersonTemplate expected : repository.findAll()) {
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
