package main;

import data.PersonRepository;
import domain.ExportImport;
import domain.InputBoundary;
import domain.Presenter;
import domain.ResponseBuilder;
import domain.addperson.AddPersonUseCase;
import domain.deleteperson.DeletePersonUseCase;
import domain.exportfile.ExportUseCase;
import domain.importfile.ImportUseCase;
import domain.refresh.RefreshUseCase;
import ui.contoller.UseCaseFactory;

public class UseCaseFactoryImpl implements UseCaseFactory {
    private final PersonRepository repository;
    private final ExportImport exportImport = new ExportImport();
    private final ResponseBuilder builder;

    public UseCaseFactoryImpl(PersonRepository repository, ResponseBuilder builder) {
        this.repository = repository;
        this.builder = builder;
    }

    @Override
    public InputBoundary make(String useCase, Presenter presenter) {
        InputBoundary refreshUseCase = new RefreshUseCase(repository, builder, presenter);

        if (useCase.equals("RefreshUseCase"))
            return refreshUseCase;
        if (useCase.equals("AddPersonUseCase"))
            return new AddPersonUseCase(repository, refreshUseCase);
        if (useCase.equals("DeletePersonUseCase"))
            return new DeletePersonUseCase(repository, refreshUseCase);
        if (useCase.equals("ExportUseCase"))
            return new ExportUseCase(repository, exportImport, refreshUseCase);
        if (useCase.equals("ImportUseCase"))
            return new ImportUseCase(exportImport, repository, refreshUseCase);
        return null;
    }
}
