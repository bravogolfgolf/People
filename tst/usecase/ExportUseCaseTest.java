package usecase;

import database.PersonRepositoryExportImport;
import database.PersonRepositoryInMemory;
import gateway.ExportImport;
import gateway.PersonRepository;
import org.junit.Test;
import responder.ExportResponder;
import responder.ExportResponse;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExportUseCaseTest implements ExportResponder {
    private ExportResponse response;

    @Override
    public void present(ExportResponse response) {
        this.response = response;
    }

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new PersonRepositoryExportImport(repository);
    private final ExportUseCase useCase = new ExportUseCase(exportImport, this);
    private final ExportRequest request = new ExportRequest();

    @Test
    public void shouldExportPersonRepositoryToFile() {
        repository.addPerson("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        File file = new File("ExportTest.per");
        request.file = file;
        assertTrue(!file.delete());
        useCase.execute(request);
        assertEquals(1, response.getCount());
        assertTrue(file.delete());
    }

    @Test(expected = ExportUseCase.ExportFailed.class)
    public void shouldThrowException() {
        request.file = new File("/bad_path/ExportTest.per");
        useCase.execute(request);
    }
}
