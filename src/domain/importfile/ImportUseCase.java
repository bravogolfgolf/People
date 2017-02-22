package domain.importfile;

import domain.Person;
import domain.Request;
import domain.UseCase;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImportUseCase implements UseCase {
    private final Import importer;
    private final ImportGateway repository;

    public ImportUseCase(Import importer, ImportGateway repository) {
        this.importer = importer;
        this.repository = repository;
    }

    @Override
    public void execute(Request request) {
        ImportRequest r = (ImportRequest) request;
        repository.setPeople(tryImportFile(r.file));
    }

    private Map<Integer, Person> tryImportFile(File file) {
        Map<Integer,Person> result;
        try {
            result = importer.fromDisk(file);
        } catch (IOException | ClassNotFoundException e) {
            throw new ImportFailed(e);
        }
        return result;
    }

    public class ImportFailed extends RuntimeException{
        ImportFailed(Exception e) {
            super(e);
        }
    }
}
