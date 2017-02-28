package usecase;

import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import database.PersonRepositoryExportImport;
import exportimportgateway.ExportImport;
import org.junit.Test;
import usecase.exportfile.ExportRequest;
import usecase.exportfile.ExportUseCase;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ExportUseCaseTest {
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new PersonRepositoryExportImport(repository);
    private final ExportUseCase useCase = new ExportUseCase(exportImport, null);
    private final ExportRequest request = new ExportRequest();

    @Test
    public void shouldExportPersonRepositoryToFile() throws IOException {
        repository.addPerson("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        File file = new File("ExportTest.per");
        request.file = file;
        assertTrue(!file.delete());
        useCase.execute(request);
        assertTrue(file.delete());
    }

    @Test(expected = ExportUseCase.ExportFailed.class)
    public void shouldThrowException() throws IOException {
        request.file = new File("/bad_path/ExportTest.per");
        useCase.execute(request);
    }
}
