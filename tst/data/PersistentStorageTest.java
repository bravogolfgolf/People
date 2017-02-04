package data;

import domain.Person;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class PersistentStorageTest {


    @Test
    public void shouldSaveMapToFileAndLoadMapFromFileToRecreateMap() throws IOException {
        File file = new File("Test.per");
        final Person person = new Person("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        Map<Integer, Person> people = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};

        writeToFile(people, file);
        Map<Integer, Person> loaded = loadFromFile(file);

        for (Integer key : people.keySet()) {
            assertEquals(people.get(key).getFullName(), loaded.get(key).getFullName());

        }
    }

    private void writeToFile(Map<Integer, Person> map, File file) throws IOException {
        List<Person> list = new ArrayList<>(map.values());
        Person[] array = list.toArray(new Person[list.size()]);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(array);
            }
        }
    }

    private Map<Integer, Person> loadFromFile(File file) throws IOException {
        Map<Integer, Person> map = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(file)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                try {
                    Person[] people = (Person[]) ois.readObject();
                    List<Person> list = Arrays.asList(people);
                    for (Person person : list) {
                        map.put(person.getId(), person);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}