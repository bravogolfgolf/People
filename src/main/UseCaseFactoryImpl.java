package main;

import data.PersonRepository;
import domain.ExportImport;
import domain.InputBoundary;
import domain.ResponseBuilder;
import domain.addperson.AddPersonUseCase;
import domain.deleteperson.DeletePersonUseCase;
import domain.exportfile.ExportUseCase;
import domain.importfile.ImportUseCase;
import domain.refresh.RefreshUseCase;
import ui.PersonTablePanelPresenter;

public class UseCaseFactoryImpl implements ui.UseCaseFactory {
    private final PersonRepository repository;
    private final ExportImport exportImport;
    private final PersonTablePanelPresenter presenter;
    private final ResponseBuilder builder;

    public UseCaseFactoryImpl(PersonRepository repository, ExportImport exportImport, ResponseBuilder builder, PersonTablePanelPresenter presenter) {
        this.repository = repository;
        this.exportImport = exportImport;
        this.builder = builder;
        this.presenter = presenter;
    }

    @Override
    public InputBoundary make(String useCase) {
        InputBoundary refreshUseCase = new RefreshUseCase(repository, builder, presenter);

        if (useCase.equals("RefreshUseCase"))
            return refreshUseCase;
        if (useCase.equals("AddPersonUseCase"))
            return new AddPersonUseCase(repository, refreshUseCase);
        if (useCase.equals("DeletePersonUseCase"))
            return new DeletePersonUseCase(repository, refreshUseCase);
        if (useCase.equals("ExportUseCase"))
            return new ExportUseCase(repository, exportImport);
        if (useCase.equals("ImportUseCase"))
            return new ImportUseCase(exportImport, repository, refreshUseCase);
        return null;
    }
}
