package data;

import domain.Person;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class PersistentTest {

    private final File file = new File("Test.per");

    @Test
    public void shouldSaveMapToFileAndLoadMapFromFileToRecreateMap() throws IOException {
        assertTrue(!deleteFile());

        Person person = new Person("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        Map<Integer, Person> people = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};

        Persistent.export(people, file);
        Map<Integer, Person> loaded = Persistent.load(file);

        for (Integer key : people.keySet()) {
            assertEquals(people.get(key).getId(), loaded.get(key).getId());
            assertEquals(people.get(key).getFullName(), loaded.get(key).getFullName());
            assertEquals(people.get(key).getOccupation(), loaded.get(key).getOccupation());
            assertEquals(people.get(key).getAgeCategory(), loaded.get(key).getAgeCategory());
            assertEquals(people.get(key).getEmploymentStatus(), loaded.get(key).getEmploymentStatus());
            assertTrue(loaded.get(key).isUsCitizen());
            assertEquals(people.get(key).getTaxId(), loaded.get(key).getTaxId());
            assertEquals(people.get(key).getGender(), loaded.get(key).getGender());

        }
        assertTrue(deleteFile());
    }

    private Boolean deleteFile() {
        return file.delete();
    }
}