package usecase;

import database.PersonRepository;
import exportimport.ExportImport;
import requestor.InputBoundary;
import respondor.Presenter;
import usecase.addperson.AddPersonUseCase;
import usecase.deleteperson.DeletePersonUseCase;
import usecase.exportfile.ExportUseCase;
import usecase.importfile.ImportUseCase;
import usecase.refresh.RefreshUseCase;
import contoller.UseCaseFactory;

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
            return new ImportUseCase(exportImport, refreshUseCase);
        return null;
    }
}
