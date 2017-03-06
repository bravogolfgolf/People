package builderfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ControllerFactory {

    private final RequestBuilder builder;
    private final UseCaseFactory factory;
    private final Map<String, Class> controllers;
    private final Map<String, Class[]> constructorClasses;

    public ControllerFactory(RequestBuilder builder, UseCaseFactory factory, Map<String, Class> controllers, Map<String, Class[]> constructorClasses) {
        this.builder = builder;
        this.factory = factory;
        this.controllers = controllers;
        this.constructorClasses = constructorClasses;
    }

    public Controller make(String string, Map<Integer, Object> requestArgs, Object responder) {
        Request request = builder.make(string, requestArgs);
        UseCase useCase = factory.make(string, responder);
        Class<?> aClass = controllers.get(string);

        Constructor constructor = null;
        try {
            constructor = aClass.getConstructor(constructorClasses.get(string));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object newClass = null;
        try {
            assert constructor != null;
            newClass = constructor.newInstance(request, useCase, responder);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return (Controller) newClass;
    }
}