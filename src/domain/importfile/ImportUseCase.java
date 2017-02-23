package domain.importfile;

import domain.InputBoundary;
import domain.Person;
import domain.Request;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImportUseCase implements InputBoundary {
    private final Import importer;
    private final ImportGateway repository;
    private final InputBoundary refreshUseCase;

    public ImportUseCase(Import importer, ImportGateway repository, InputBoundary refreshUseCase) {
        this.importer = importer;
        this.repository = repository;
        this.refreshUseCase = refreshUseCase;
    }

    @Override
    public void execute(Request request) {
        ImportRequest r = (ImportRequest) request;
        repository.setPeople(tryImportFile(r.file));
        refreshUseCase.execute(request);
    }

    private Map<Integer, Person> tryImportFile(File file) {
        Map<Integer, Person> result;
        try {
            result = importer.fromDisk(file);
        } catch (IOException | ClassNotFoundException e) {
            throw new ImportFailed(e);
        }
        return result;
    }

    public class ImportFailed extends RuntimeException {
        ImportFailed(Exception e) {
            super(e);
        }
    }
}
