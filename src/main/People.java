package main;

import database.PersonRepositoryExportImport;
import database.PersonRepositoryMySQL;
import databasegateway.PersonRepository;
import exportimportgateway.ExportImport;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Presenter;
import usecase.addperson.AddPersonRequest;
import usecase.addperson.AddPersonUseCase;
import usecase.deleteperson.DeletePersonRequest;
import usecase.deleteperson.DeletePersonUseCase;
import usecase.exportfile.ExportRequest;
import usecase.exportfile.ExportUseCase;
import usecase.importfile.ImportRequest;
import usecase.importfile.ImportUseCase;
import usecase.refresh.RefreshRequest;
import usecase.refresh.RefreshUseCase;
import view.MainFrame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

class People {

    private static final Map<String, Class<? extends Request>> requests = new HashMap<>();

    private static final Map<String, Class<? extends UseCase>> useCases = new HashMap<>();
    private static final Map<String, Class<?>[]> constructorClasses = new HashMap<>();
    private static final Map<String, Object> constructorObjects = new HashMap<>();

    private static PersonRepositoryExportImport exportImport;
    private static PersonRepository repository;

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {
            repository = new PersonRepositoryMySQL();
            exportImport = new PersonRepositoryExportImport(repository);

            setRequests();
            RequestBuilder requestBuilder = new RequestBuilder(requests);

            setUseCases();
            setConstructorClasses();
            setConstructorObjects();
            UseCaseFactory useCaseFactory = new UseCaseFactory(useCases, constructorClasses, constructorObjects);

            MainFrame mainFrame = new MainFrame(requestBuilder, useCaseFactory);
            mainFrame.initialize();
        });
    }

    private static void setRequests() {
        requests.put("RefreshRequest", RefreshRequest.class);
        requests.put("AddPersonRequest", AddPersonRequest.class);
        requests.put("DeletePersonRequest", DeletePersonRequest.class);
        requests.put("ExportRequest", ExportRequest.class);
        requests.put("ImportRequest", ImportRequest.class);
    }

    private static void setUseCases() {
        useCases.put("RefreshUseCase", RefreshUseCase.class);
        useCases.put("AddPersonUseCase", AddPersonUseCase.class);
        useCases.put("DeletePersonUseCase", DeletePersonUseCase.class);
        useCases.put("ExportUseCase", ExportUseCase.class);
        useCases.put("ImportUseCase", ImportUseCase.class);
    }

    private static void setConstructorClasses() {
        constructorClasses.put("RefreshUseCase", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("AddPersonUseCase", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("DeletePersonUseCase", new Class[]{PersonRepository.class, Presenter.class});
        constructorClasses.put("ExportUseCase", new Class[]{ExportImport.class, Presenter.class});
        constructorClasses.put("ImportUseCase", new Class[]{ExportImport.class, Presenter.class});
    }

    private static void setConstructorObjects() {
        constructorObjects.put("RefreshUseCase", repository);
        constructorObjects.put("AddPersonUseCase", repository);
        constructorObjects.put("DeletePersonUseCase", repository);
        constructorObjects.put("ExportUseCase", exportImport);
        constructorObjects.put("ImportUseCase", exportImport);
    }
}