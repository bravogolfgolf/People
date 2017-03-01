package main;

import contoller.*;
import contollerfactory.Controller;
import contollerfactory.ControllerFactory;
import database.PersonRepositoryExportImport;
import database.PersonRepositoryMySQL;
import databasegateway.PersonRepository;
import exportimportgateway.ExportImport;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Presenter;
import view.View;
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
import ui.MainFrame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

class People {
    private static final Map<String, Class<? extends Controller>> controllers = new HashMap<>();
    private static final Map<String, Class<?>[]> controllerConstructorClasses = new HashMap<>();

    private static final Map<String, Class<? extends Request>> requests = new HashMap<>();

    private static final Map<String, Class<? extends UseCase>> useCases = new HashMap<>();
    private static final Map<String, Class<?>[]> useCaseConstructorClasses = new HashMap<>();
    private static final Map<String, Object[]> useCaseConstructorObjects = new HashMap<>();

    private static PersonRepository repository;
    private static PersonRepositoryExportImport exportImport;

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {
            repository = new PersonRepositoryMySQL();
            exportImport = new PersonRepositoryExportImport(repository);

            registration();
            RequestBuilder requestBuilder = new RequestBuilder(requests);
            UseCaseFactory useCaseFactory = new UseCaseFactory(useCases, useCaseConstructorClasses, useCaseConstructorObjects);
            ControllerFactory controllerFactory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, controllerConstructorClasses);

            MainFrame mainFrame = new MainFrame(controllerFactory);
            mainFrame.initialize();
        });
    }

    private static void registration() {
        refreshRegistration();
        addPersonRegistration();
        deletePersonRegistration();
        exportRegistration();
        importRegistration();
    }

    private static void refreshRegistration() {
        controllers.put("RefreshController", RefreshController.class);
        controllerConstructorClasses.put("RefreshController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        requests.put("RefreshRequest", RefreshRequest.class);
        useCases.put("RefreshUseCase", RefreshUseCase.class);
        useCaseConstructorClasses.put("RefreshUseCase", new Class[]{PersonRepository.class, Presenter.class});
        useCaseConstructorObjects.put("RefreshUseCase", new Object[]{repository});

    }

    private static void addPersonRegistration() {
        controllers.put("AddPersonController", AddPersonController.class);
        controllerConstructorClasses.put("AddPersonController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        requests.put("AddPersonRequest", AddPersonRequest.class);
        useCases.put("AddPersonUseCase", AddPersonUseCase.class);
        useCaseConstructorClasses.put("AddPersonUseCase", new Class[]{PersonRepository.class, Presenter.class});
        useCaseConstructorObjects.put("AddPersonUseCase", new Object[]{repository});
    }

    private static void deletePersonRegistration() {
        controllers.put("DeletePersonController", DeletePersonController.class);
        controllerConstructorClasses.put("DeletePersonController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        requests.put("DeletePersonRequest", DeletePersonRequest.class);
        useCases.put("DeletePersonUseCase", DeletePersonUseCase.class);
        useCaseConstructorClasses.put("DeletePersonUseCase", new Class[]{PersonRepository.class, Presenter.class});
        useCaseConstructorObjects.put("DeletePersonUseCase", new Object[]{repository});

    }

    private static void exportRegistration() {
        controllers.put("ExportController", ExportController.class);
        controllerConstructorClasses.put("ExportController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        requests.put("ExportRequest", ExportRequest.class);
        useCases.put("ExportUseCase", ExportUseCase.class);
        useCaseConstructorClasses.put("ExportUseCase", new Class[]{ExportImport.class, Presenter.class});
        useCaseConstructorObjects.put("ExportUseCase", new Object[]{exportImport});
    }

    private static void importRegistration() {
        controllers.put("ImportController", ImportController.class);
        controllerConstructorClasses.put("ImportController", new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, Presenter.class, View.class});
        requests.put("ImportRequest", ImportRequest.class);
        useCases.put("ImportUseCase", ImportUseCase.class);
        useCaseConstructorClasses.put("ImportUseCase", new Class[]{ExportImport.class, Presenter.class});
        useCaseConstructorObjects.put("ImportUseCase", new Object[]{exportImport});
    }
}