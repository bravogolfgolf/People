package data;

import domain.PersistentInteractor;
import domain.Person;

import java.io.*;
import java.util.*;

public class Persistent implements PersistentInteractor {

    @Override
    public void export(Map<Integer, Person> map, File file) throws IOException {
        List<Person> list = new ArrayList<>(map.values());
        Person[] array = list.toArray(new Person[list.size()]);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(array);
        }
    }

    @Override
    public Map<Integer, Person> getImport(File file) throws IOException, ClassNotFoundException {
        Map<Integer, Person> map = new HashMap<>();
        Person[] people;
        List<Person> list;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            people = (Person[]) ois.readObject();
        }
        list = Arrays.asList(people);
        for (Person person : list)
            map.put(person.getId(), person);
        return map;
    }

}
