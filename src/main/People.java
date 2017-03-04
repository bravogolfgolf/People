package main;

import builderfactory.ControllerFactory;
import builderfactory.RequestBuilder;
import builderfactory.UseCaseFactory;
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
        add(new String[]{"Refresh", "controller.RefreshController", "responder.RefreshResponder", "responder.View", "usecase.RefreshRequest", "usecase.RefreshUseCase", "gateway.PersonRepository"});
        add(new String[]{"AddPerson", "controller.AddPersonController", "responder.AddPersonResponder", "responder.View", "usecase.AddPersonRequest", "usecase.AddPersonUseCase", "gateway.PersonRepository"});
        add(new String[]{"DeletePerson", "controller.DeletePersonController", "responder.DeletePersonResponder", "responder.View", "usecase.DeletePersonRequest", "usecase.DeletePersonUseCase", "gateway.PersonRepository"});
        add(new String[]{"Export", "controller.ExportController", "responder.ExportResponder", "responder.View", "usecase.ExportRequest", "usecase.ExportUseCase", "gateway.ExportImport"});
        add(new String[]{"Import", "controller.ImportController", "responder.ImportResponder", "responder.View", "usecase.ImportRequest", "usecase.ImportUseCase", "gateway.ExportImport"});
    }};
    private static final Map<String, Class> controllers = new HashMap<>();
    private static final Map<String, Class[]> controllerConstructorClasses = new HashMap<>();
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
        createGatewaysMap();
        for (String[] entry : registry)
            createAllOtherMaps(entry);
        RequestBuilder requestBuilder = new RequestBuilder(requests);
        UseCaseFactory useCaseFactory = new UseCaseFactory(useCases, useCaseConstructorClasses, useCaseConstructorObjects);
        controllerFactory = new ControllerFactory(requestBuilder, useCaseFactory, controllers, controllerConstructorClasses);
    }

    private static void createGatewaysMap() {
        Class<?> repositoryClass = tryGetClass("database.PersonRepositoryMySQL");
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
        String key = entry[0];
        Class controllerClass = tryGetClass(entry[1]);
        Class presenterClass = tryGetClass(entry[2]);
        Class viewClass = tryGetClass(entry[3]);
        Class requestClass = tryGetClass(entry[4]);
        Class useCaseClass = tryGetClass(entry[5]);
        Class gatewayClass = tryGetClass(entry[6]);

        controllers.put(key, controllerClass);
        controllerConstructorClasses.put(key, new Class[]{RequestBuilder.class, Map.class, UseCaseFactory.class, presenterClass, viewClass});
        requests.put(key, requestClass);
        useCases.put(key, useCaseClass);
        useCaseConstructorClasses.put(key, new Class[]{gatewayClass, presenterClass});
        useCaseConstructorObjects.put(key, new Object[]{gateways.get(gatewayClass)});
    }

    private static void runApplication() {
        Class<?> applicationClass = tryGetClass("ui_swing.MainFrame");
        Constructor constructor = tryGetConstructor(applicationClass, new Class[]{ControllerFactory.class});
        Runnable applicationObject = (Runnable) getObject(controllerFactory, constructor);
        applicationObject.run();
    }
}