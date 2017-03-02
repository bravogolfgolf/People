package main;

import contollerfactory.ControllerFactory;
import database.PersonRepositoryExportImport;
import database.PersonRepositoryMySQL;
import databasegateway.PersonRepository;
import exportimportgateway.ExportImport;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import ui.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class People {
    private static final Map<String, Class<?>> controllers = new HashMap<>();
    private static final Map<String, Class<?>[]> controllerConstructorClasses = new HashMap<>();
    private static final Map<String, Class<?>> requests = new HashMap<>();
    private static final Map<String, Class<?>> useCases = new HashMap<>();
    private static final Map<String, Class<?>[]> useCaseConstructorClasses = new HashMap<>();
    private static final Map<String, Object[]> useCaseConstructorObjects = new HashMap<>();
    private static final List<String[]> registry = new ArrayList<String[]>() {{
        add(new String[]{"Refresh", "controller.RefreshController", "responder.Presenter", "view.View", "usecase.RefreshRequest", "usecase.RefreshUseCase", "databasegateway.PersonRepository"});
        add(new String[]{"AddPerson", "controller.AddPersonController", "responder.Presenter", "view.View", "usecase.AddPersonRequest", "usecase.AddPersonUseCase", "databasegateway.PersonRepository"});
        add(new String[]{"DeletePerson", "controller.DeletePersonController", "responder.Presenter", "view.View", "usecase.DeletePersonRequest", "usecase.DeletePersonUseCase", "databasegateway.PersonRepository"});
        add(new String[]{"Export", "controller.ExportController", "responder.Presenter", "view.View", "usecase.ExportRequest", "usecase.ExportUseCase", "exportimportgateway.ExportImport"});
        add(new String[]{"Import", "controller.ImportController", "responder.Presenter", "view.View", "usecase.ImportRequest", "usecase.ImportUseCase", "exportimportgateway.ExportImport"});
    }};
    private static final Map<Class, Object> gateways = new HashMap<>();

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {

            PersonRepository repository = new PersonRepositoryMySQL();
            PersonRepositoryExportImport exportImport = new PersonRepositoryExportImport(repository);
            gateways.put(PersonRepository.class, repository);
            gateways.put(ExportImport.class, exportImport);

            for (String[] entry : registry)
                register(entry);

            RequestBuilder requestBuilder = new RequestBuilder(requests);
            UseCaseFactory useCaseFactory = new UseCaseFactory(useCases, useCaseConstructorClasses, useCaseConstructorObjects);
            ControllerFactory controllerFactory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, controllerConstructorClasses);

            MainFrame mainFrame = new MainFrame(controllerFactory);
            mainFrame.initialize();
        });
    }

    private static void register(String[] entry) {

        String key = entry[0];

        Class controllerClass = null;
        try {
            controllerClass = Class.forName(entry[1]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class presenterClass = null;
        try {
            presenterClass = Class.forName(entry[2]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class viewClass = null;
        try {
            viewClass = Class.forName(entry[3]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class requestClass = null;
        try {
            requestClass = Class.forName(entry[4]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class useCaseClass = null;
        try {
            useCaseClass = Class.forName(entry[5]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class gatewayClass = null;
        try {
            gatewayClass = Class.forName(entry[6]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        controllers.put(key, controllerClass);
        controllerConstructorClasses.put(key, new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, presenterClass, viewClass});
        requests.put(key, requestClass);
        useCases.put(key, useCaseClass);
        useCaseConstructorClasses.put(key, new Class[]{gatewayClass, presenterClass});
        useCaseConstructorObjects.put(key, new Object[]{gateways.get(gatewayClass)});
    }
}