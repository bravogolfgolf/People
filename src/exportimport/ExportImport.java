package exportimport;

import exportimportgateway.Export;
import exportimportgateway.Import;
import entity.PersonTemplate;
import databasegateway.ExportImportGateway;

import java.io.*;
import java.util.*;

public class ExportImport implements Export, Import {

    private final ExportImportGateway repository;

    public ExportImport(ExportImportGateway repository) {
        this.repository = repository;
    }

    @Override
    public void toDisk(File file) throws IOException {
        List<PersonTemplate> list = new ArrayList<>(repository.getPeople().values());
        PersonTemplate[] array = list.toArray(new PersonTemplate[list.size()]);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(array);
        }
    }

    @Override
    public void fromDisk(File file) throws IOException, ClassNotFoundException {
        Map<Integer, PersonTemplate> map = new HashMap<>();
        PersonTemplate[] people;
        List<PersonTemplate> list;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            people = (PersonTemplate[]) ois.readObject();
        }
        list = Arrays.asList(people);
        for (PersonTemplate person : list)
            map.put(person.getId(), person);
        repository.setPeople(map);
    }

}
