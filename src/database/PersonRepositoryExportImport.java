package database;

import gateway.PersonRepository;
import gateway.ExportImport;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PersonRepositoryExportImport implements ExportImport {

    private final PersonRepository repository;

    public PersonRepositoryExportImport(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public int toDisk(File file) throws IOException {
        Collection list = repository.forExport();
        Person[] array = new Person[list.size()];
        int count = 0;
        for (Object object : list) {
            array[count++] = (Person) object;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(array);
        }
        return count;
    }

    @Override
    public void fromDisk(File file) throws IOException, ClassNotFoundException {
        Person[] array;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            array = (Person[]) ois.readObject();
        }
        List<Person> list = Arrays.asList(array);
        repository.fromImport(list);
    }
}
