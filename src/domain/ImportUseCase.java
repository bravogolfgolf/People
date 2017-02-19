package domain;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImportUseCase implements UseCase {
    private final PersistentInteractor exportImport;
    private final RepositoryInteractor repository;
    private final PresenterInteractor presenter;

    ImportUseCase(PersistentInteractor exportImport, RepositoryInteractor repository, PresenterInteractor presenter) {
        this.exportImport = exportImport;
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        ImportRequest r = (ImportRequest) request;
        File file = new File(r.file);
        repository.setPeople(tryImportFile(file));
        presenter.presentPeople(repository.getPeople());
    }

    private Map<Integer, Person> tryImportFile(File file) {
        Map<Integer,Person> result;
        try {
            result = exportImport.getImport(file);
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
