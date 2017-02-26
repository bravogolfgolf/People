package main;

import contoller.ControllerFactoryImpl;
import database.PersonRepository;
import database.PersonRepositoryMySQL;
import databasegateway.AddPersonGateway;
import databasegateway.DeletePersonGateway;
import databasegateway.RefreshGateway;
import exportimport.ExportImport;
import exportimportgateway.Export;
import exportimportgateway.Import;
import requestor.InputBoundary;
import responder.Presenter;
import usecase.RequestBuilderImpl;
import usecase.UseCaseFactoryImpl;
import usecase.addperson.AddPersonUseCase;
import usecase.deleteperson.DeletePersonUseCase;
import usecase.exportfile.ExportUseCase;
import usecase.importfile.ImportUseCase;
import usecase.refresh.RefreshUseCase;
import view.MainFrame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

class People {

    private static final Map<String, Class<? extends InputBoundary>> useCases = new HashMap<>();
    private static final Map<String, Class<?>[]> constructorClasses = new HashMap<>();
    private static final Map<String, Object> constructorObjects = new HashMap<>();
    private static ExportImport exportImport;
    private static PersonRepository repository;

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {
            repository = new PersonRepositoryMySQL();
            exportImport = new ExportImport(repository);

            setUseCases();
            setConstructorClasses();
            setConstructorObjects();

            RequestBuilderImpl requestBuilder = new RequestBuilderImpl();
            UseCaseFactoryImpl useCaseFactory = new UseCaseFactoryImpl(useCases, constructorClasses, constructorObjects);
            ControllerFactoryImpl controllerFactory = new ControllerFactoryImpl(requestBuilder, useCaseFactory);
            MainFrame mainFrame = new MainFrame(controllerFactory);
            mainFrame.initialize();
        });
    }

    private static void setUseCases() {
        useCases.put("RefreshUseCase", RefreshUseCase.class);
        useCases.put("AddPersonUseCase", AddPersonUseCase.class);
        useCases.put("DeletePersonUseCase", DeletePersonUseCase.class);
        useCases.put("ExportUseCase", ExportUseCase.class);
        useCases.put("ImportUseCase", ImportUseCase.class);
    }

    private static void setConstructorClasses() {
        constructorClasses.put("RefreshUseCase", new Class[]{RefreshGateway.class, Presenter.class});
        constructorClasses.put("AddPersonUseCase", new Class[]{AddPersonGateway.class, Presenter.class});
        constructorClasses.put("DeletePersonUseCase", new Class[]{DeletePersonGateway.class, Presenter.class});
        constructorClasses.put("ExportUseCase", new Class[]{Export.class, Presenter.class});
        constructorClasses.put("ImportUseCase", new Class[]{Import.class, Presenter.class});
    }

    private static void setConstructorObjects() {
        constructorObjects.put("RefreshUseCase", repository);
        constructorObjects.put("AddPersonUseCase", repository);
        constructorObjects.put("DeletePersonUseCase", repository);
        constructorObjects.put("ExportUseCase", exportImport);
        constructorObjects.put("ImportUseCase", exportImport);
    }
}