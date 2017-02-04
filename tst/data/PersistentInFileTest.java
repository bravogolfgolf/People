package data;

import domain.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class PersistentInFileTest {

    private File file = new File("Test.per");

    @Before
    public void setUp() {
        deleteFile();
    }

    @After
    public void tearDown() {
        deleteFile();

    }

    private void deleteFile() {
        file.delete();
    }

    @Test
    public void shouldSaveMapToFileAndLoadMapFromFileToRecreateMap() throws IOException {
        Person person = new Person("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        Map<Integer, Person> people = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};

        PersistentInFile.writeToFile(people, file);
        Map<Integer, Person> loaded = PersistentInFile.loadFromFile(file);

        for (Integer key : people.keySet()) {
            assertEquals(people.get(key).getFullName(), loaded.get(key).getFullName());

        }
    }
}