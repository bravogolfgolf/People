package data;

import domain.PersistentInteractor;
import domain.Person;

import java.io.*;
import java.util.*;

public class Persistent implements PersistentInteractor {

    @Override
    public void export(Map<Integer, Person> map, File file) {
        List<Person> list = new ArrayList<>(map.values());
        Person[] array = list.toArray(new Person[list.size()]);
        FileOutputStream fos = getObjectInputStream(file);
        ObjectOutputStream oos = getObjectOutputStream(fos);
        writeArray(array, oos);
    }

    @Override
    public Map<Integer, Person> load(File file) {
        Map<Integer, Person> map = new HashMap<>();
        FileInputStream fis = getFileInputStream(file);
        ObjectInputStream ois = getObjectInputStream(fis);
        Person[] people = readArray(ois);
        List<Person> list = Arrays.asList(people);
        for (Person person : list)
            map.put(person.getId(), person);
        return map;
    }

    private Person[] readArray(ObjectInputStream ois) {
        Person[] people = null;
        try {
            people = (Person[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return people;
    }

    private FileOutputStream getObjectInputStream(File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fos;
    }

    private ObjectOutputStream getObjectOutputStream(FileOutputStream fos) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oos;
    }

    private void writeArray(Person[] array, ObjectOutputStream oos) {
        try {
            oos.writeObject(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileInputStream getFileInputStream(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fis;
    }

    private ObjectInputStream getObjectInputStream(FileInputStream fis) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ois;
    }
}
