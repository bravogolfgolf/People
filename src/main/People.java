package main;

import builderfactory.*;
import gateway.ExportImport;
import gateway.PersonRepository;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class People {
    private static final Map<Class, Object> gateways = new HashMap<>();
    private static final List<String[]> registry = new ArrayList<String[]>() {{
        add(new String[]{"Refresh", "controller.RefreshController", "usecase.RefreshRequest", "usecase.RefreshUseCase", "gateway.PersonRepository", "responder.RefreshResponder"});
        add(new String[]{"AddPerson", "controller.AddPersonController", "usecase.AddPersonRequest", "usecase.AddPersonUseCase", "gateway.PersonRepository", "responder.AddPersonResponder"});
        add(new String[]{"DeletePerson", "controller.DeletePersonController", "usecase.DeletePersonRequest", "usecase.DeletePersonUseCase", "gateway.PersonRepository", "responder.DeletePersonResponder"});
        add(new String[]{"Export", "controller.ExportController", "usecase.ExportRequest", "usecase.ExportUseCase", "gateway.ExportImport", "responder.ExportResponder"});
        add(new String[]{"Import", "controller.ImportController", "usecase.ImportRequest", "usecase.ImportUseCase", "gateway.ExportImport", "responder.ImportResponder"});
        add(new String[]{"UpdatePerson", "controller.UpdatePersonController", "usecase.UpdatePersonRequest", "usecase.UpdatePersonUseCase", "gateway.PersonRepository", "responder.UpdatePersonResponder"});
    }};
    private static final Map<String, Class> controllers = new HashMap<>();
    private static Class[] controllerConstructorClasses = new Class[3];
    private static final Map<String, Class> requests = new HashMap<>();
    private static final Map<String, Class> useCases = new HashMap<>();
    private static final Map<String, Class[]> useCaseConstructorClasses = new HashMap<>();
    private static final Map<String, Object[]> useCaseConstructorObjects = new HashMap<>();
    private static ControllerFactory controllerFactory;

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {
            setContext();
            runApplication();
        });
    }

    private static void setContext() {
        createControllerConstructorClasses();
        createGatewaysMap();
        for (String[] entry : registry)
            createAllOtherMaps(entry);
        RequestBuilder requestBuilder = new RequestBuilder(requests);
        UseCaseFactory useCaseFactory = new UseCaseFactory(useCases, useCaseConstructorClasses, useCaseConstructorObjects);
        controllerFactory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, controllerConstructorClasses);
    }

    private static void createControllerConstructorClasses() {
        Class controllerClass = tryGetClass("responder.Responder");
        controllerConstructorClasses = new Class[]{Request.class, UseCase.class, controllerClass};
    }

    private static void createGatewaysMap() {
        Class<?> repositoryClass = tryGetClass("database.PersonRepositoryMySQL");
//        Class<?> repositoryClass = tryGetClass("database.PersonRepositoryInMemory");
        Object repositoryObject = getObject(repositoryClass);
        Class<?> exportImportClass = tryGetClass("database.PersonRepositoryExportImport");
        Constructor constructor = tryGetConstructor(exportImportClass, new Class[]{PersonRepository.class});
        Object exportImportObject = getObject(repositoryObject, constructor);
        gateways.put(PersonRepository.class, repositoryObject);
        gateways.put(ExportImport.class, exportImportObject);
    }

    private static Class tryGetClass(String className) {
        Class aClass = null;
        try {
            aClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aClass;
    }

    private static Object getObject(Class<?> repositoryClass) {
        Object repositoryObject = null;
        try {
            assert repositoryClass != null;
            repositoryObject = repositoryClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return repositoryObject;
    }

    private static Constructor tryGetConstructor(Class<?> aClass, Class[] args) {
        Constructor constructor = null;
        try {
            assert aClass != null;
            constructor = aClass.getConstructor(args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return constructor;
    }

    private static Object getObject(Object repositoryObject, Constructor exportImportConstructor) {
        Object exportImportObject = null;
        try {
            assert exportImportConstructor != null;
            exportImportObject = exportImportConstructor.newInstance(repositoryObject);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return exportImportObject;
    }

    private static void createAllOtherMaps(String[] entry) {
        int i = 0;
        String key = entry[i++];
        Class controllerClass = tryGetClass(entry[i++]);
        Class requestClass = tryGetClass(entry[i++]);
        Class useCaseClass = tryGetClass(entry[i++]);
        Class gatewayClass = tryGetClass(entry[i++]);
        Class responderClass = tryGetClass(entry[i]);

        controllers.put(key, controllerClass);
        requests.put(key, requestClass);
        useCases.put(key, useCaseClass);
        useCaseConstructorClasses.put(key, new Class[]{gatewayClass, responderClass});
        useCaseConstructorObjects.put(key, new Object[]{gateways.get(gatewayClass)});
    }

    private static void runApplication() {
        Class<?> applicationClass = tryGetClass("ui_swing.MainFrame");
        Constructor constructor = tryGetConstructor(applicationClass, new Class[]{ControllerFactory.class});
        Runnable applicationObject = (Runnable) getObject(controllerFactory, constructor);
        applicationObject.run();
    }
}