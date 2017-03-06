package usecase;

import database.PersonRepositoryExportImport;
import database.PersonRepositoryInMemory;
import gateway.ExportImport;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.ImportResponder;
import responder.ImportResponse;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImportUseCaseTest implements ImportResponder {
    private ImportResponse response;

    @Override
    public void present(ImportResponse response) {
        this.response = response;
    }

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new PersonRepositoryExportImport(repository);
    private final ImportUseCase useCase = new ImportUseCase(exportImport, this);
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
        assertEquals(1, response.getCount());
    }

    @Test(expected = ImportUseCase.ImportFailed.class)
    public void shouldThrowException() {
        request.file = new File("/bad_path/ImportTest.per");
        useCase.execute(request);
    }
}
