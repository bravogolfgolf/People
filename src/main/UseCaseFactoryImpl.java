package main;

import data.PersonRepository;
import domain.ExportImport;
import domain.InputBoundary;
import domain.Presenter;
import domain.addperson.AddPersonUseCase;
import domain.deleteperson.DeletePersonUseCase;
import domain.exportfile.ExportUseCase;
import domain.importfile.ImportUseCase;
import domain.refresh.RefreshUseCase;
import ui.contoller.UseCaseFactory;

public class UseCaseFactoryImpl implements UseCaseFactory {
    private final PersonRepository repository;
    private final ExportImport exportImport;

    public UseCaseFactoryImpl(PersonRepository repository) {
        this.repository = repository;
        exportImport = new ExportImport(repository);
    }

    @Override
    public InputBoundary make(String useCase, Presenter presenter) {
        InputBoundary refreshUseCase = new RefreshUseCase(repository, presenter);

        if (useCase.equals("RefreshUseCase"))
            return refreshUseCase;
        if (useCase.equals("AddPersonUseCase"))
            return new AddPersonUseCase(repository, refreshUseCase);
        if (useCase.equals("DeletePersonUseCase"))
            return new DeletePersonUseCase(repository, refreshUseCase);
        if (useCase.equals("ExportUseCase"))
            return new ExportUseCase(exportImport, refreshUseCase);
        if (useCase.equals("ImportUseCase"))
            return new ImportUseCase(exportImport, repository, refreshUseCase);
        return null;
    }
}
