package domain;

import data.PersonRepositoryInMemory;
import data.PersonRepository;
import domain.exportfile.ExportRequest;
import domain.exportfile.ExportUseCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ExportUseCaseTest {

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new ExportImport();
    private final InputBoundary useCase = new ExportUseCase(repository, exportImport);
    private final ExportRequest request = new ExportRequest();
    private final File file = new File("ExportTest.per");
    private final Person person = new Person(1, "Full Name", "Occupation",
            1, 0, true,
            "123-45-6789", "Male");

    @Before
    public void setUp() {
        file.delete();
        repository.addPerson(person);
        request.file = file;
    }

    @After
    public void tearDown() {
        file.delete();
    }

    @Test
    public void shouldExportPersonRepositoryToFile() throws IOException {
        assertTrue(!file.exists());
        useCase.execute(request);
        assertTrue(file.exists());
    }

    @Test(expected = ExportUseCase.ExportFailed.class)
    public void shouldThrowException() throws IOException {
        request.file = new File("/bad_path/ExportTest.per");
        useCase.execute(request);
    }
}
