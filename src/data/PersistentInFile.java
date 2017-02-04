package data;

import domain.Person;

import java.io.*;
import java.util.*;

class PersistentInFile {

    static void writeToFile(Map<Integer, Person> map, File file) throws IOException {
        List<Person> list = new ArrayList<>(map.values());
        Person[] array = list.toArray(new Person[list.size()]);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(array);
            }
        }
    }

    static Map<Integer, Person> loadFromFile(File file) throws IOException {
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
