package contollerfactory;

import requestor.RequestBuilder;
import requestor.UseCaseFactory;

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

    public Controller make(String controller, Object[] constructorArgs) {
        Class<?> aClass = controllers.get(controller);

        Constructor constructor = null;
        try {
            constructor = aClass.getConstructor(constructorClasses.get(controller));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object newClass = null;
        try {
            assert constructor != null;
            newClass = constructor.newInstance(builder, constructorArgs[0], factory, constructorArgs[1], constructorArgs[2]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return (Controller) newClass;
    }
}