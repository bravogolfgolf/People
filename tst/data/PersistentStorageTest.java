package data;

import domain.Person;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class PersistentStorageTest {


    @Test
    public void shouldSaveMapToFileAndLoadMapFromFileToRecreateMap() throws IOException {
        File file = new File("Test.per");
        final Person person = new Person("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        Map<Integer, Person> people = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};

        writeToFile(person, file);
        Person loaded = loadFromFile(file);

        for (Integer key : people.keySet()) {
            assertEquals(people.get(key).getFullName(), loaded.getFullName());
        }
    }

    private void writeToFile(Person person, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(person);
            }
        }
    }

    private Person loadFromFile(File file) throws IOException {
        Person person = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                try {
                    person = (Person) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return person;
    }
}

