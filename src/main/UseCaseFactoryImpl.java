package main;

import data.PersonRepositoryInMemory;
import domain.*;
import ui.UseCase;

public class UseCaseFactoryImpl implements ui.UseCaseFactory {

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final Presenter presenter = new Presenter();
    private final ExportImport exportImport = new ExportImport();


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
