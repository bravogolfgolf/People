package exportimport;

import database.Person;
import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import entity.PersonTemplate;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExportImportTest {

    private final File file = new File("Test.per");
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final ExportImport exportImport = new ExportImport(repository);

    @Test
    public void shouldSaveMapToFileAndLoadMapFromFileToRecreateMap() throws IOException, ClassNotFoundException {
        assertTrue(!deleteFile());

        repository.addPerson("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");

        exportImport.toDisk(file);

        exportImport.fromDisk(file);

        List<PersonTemplate> loaded = repository.findAll();

        for (PersonTemplate personTemplate : loaded) {
            assertEquals(1, personTemplate.getId());
            assertEquals("Full Name", personTemplate.getFullName());
            assertEquals("Occupation", personTemplate.getOccupation());
            assertEquals(1, personTemplate.getAgeCategory());
            assertEquals(0, personTemplate.getEmploymentStatus());
            assertTrue(personTemplate.isUsCitizen());
            assertEquals("123-45-6789", personTemplate.getTaxId());
            assertEquals("Male", personTemplate.getGender());
        }
        assertTrue(deleteFile());
    }

    private Boolean deleteFile() {
        return file.delete();
    }
}