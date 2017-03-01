package database;

import databasegateway.PersonRepository;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonRepositoryExportImportTest {

    private final File file = new File("Test.per");
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final PersonRepositoryExportImport exportImport = new PersonRepositoryExportImport(repository);

    @Test
    public void shouldSaveMapToFileAndLoadMapFromFileToRecreateMap() throws IOException, ClassNotFoundException {
        assertTrue(!deleteFile());

        repository.addPerson("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");

        exportImport.toDisk(file);

        exportImport.fromDisk(file);

        assertEquals(1, repository.findAll().size());

        for (Object object : repository.findAll()) {
            Person person = (Person) object;
            assertEquals(1, person.getId());
            assertEquals("Full Name", person.getFullName());
            assertEquals("Occupation", person.getOccupation());
            assertEquals(1, person.getAgeCategory());
            assertEquals(0, person.getEmploymentStatus());
            assertTrue(person.isUsCitizen());
            assertEquals("123-45-6789", person.getTaxId());
            assertEquals("Male", person.getGender());
        }
        assertTrue(deleteFile());
    }

    private Boolean deleteFile() {
        return file.delete();
    }
}