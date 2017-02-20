package main;

import domain.*;
import domain.UseCase;

public class UseCaseFactoryImpl implements ui.UseCaseFactory {

    private final RepositoryInteractor repository;
    private final ExportImport exportImport;
    private final Presenter presenter;

    UseCaseFactoryImpl(RepositoryInteractor repository, ExportImport exportImport, Presenter presenter) {

        this.repository = repository;
        this.exportImport = exportImport;
        this.presenter = presenter;
    }

    @Override
    public UseCase make(String useCase) {
        if (useCase.equals("AddPersonUseCase"))
            return new AddPersonUseCase(repository, presenter);
        if (useCase.equals("DeletePersonUseCase"))
            return new DeletePersonUseCase(repository, presenter);
        if (useCase.equals("ExportUseCase"))
            return new ExportUseCase(repository, exportImport);
        if (useCase.equals("ImportUseCase"))
            return new ImportUseCase(exportImport, repository, presenter);
        return null;
    }
}
