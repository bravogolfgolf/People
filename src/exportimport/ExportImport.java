package exportimport;

import database.Person;
import databasegateway.ExportImportGateway;
import exportimportgateway.Export;
import exportimportgateway.Import;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExportImport implements Export, Import {

    private final ExportImportGateway repository;

    public ExportImport(ExportImportGateway repository) {
        this.repository = repository;
    }

    @Override
    public void toDisk(File file) throws IOException {
        List<Person> list = new ArrayList<>(repository.forExport());
        Person[] array = list.toArray(new Person[list.size()]);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(array);
        }
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
