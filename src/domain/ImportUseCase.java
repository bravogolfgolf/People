package domain;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImportUseCase implements UseCase {
    private final Import importer;
    private final RepositoryInteractor repository;
    private final PresenterInteractor presenter;

    public ImportUseCase(Import importer, RepositoryInteractor repository, PresenterInteractor presenter) {
        this.importer = importer;
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        ImportRequest r = (ImportRequest) request;
        repository.setPeople(tryImportFile(r.file));
        presenter.presentPeople(repository.getPeople());
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

    class ImportFailed extends RuntimeException{
        ImportFailed(Exception e) {
            super(e);
        }
    }
}
