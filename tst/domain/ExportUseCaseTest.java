package domain;

import data.PersonRepositoryInMemory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ExportUseCaseTest {

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new ExportImport();
    private final ExportUseCase useCase = new ExportUseCase(repository, exportImport);
    private final Person person = new Person(1, "Full Name", "Occupation",
            1, 0, true,
            "123-45-6789", "Male");
    private final File file = new File("ExportTest.per");
    private final ExportRequest request = new ExportRequest();

    @Before
    public void setUp() {
        file.delete();
        repository.addPerson(person);
        request.file = "ExportTest.per";
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
        request.file = "/bad_path/ExportTest.per";
        useCase.execute(request);
    }
}
