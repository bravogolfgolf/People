package requestor;

import responder.Presenter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class UseCaseFactory {

    private final Map<String, Class<? extends InputBoundary>> useCases;
    private final Map<String, Class<?>[]> constructorClasses;
    private final Map<String, Object> constructorObjects;

    public UseCaseFactory(Map<String, Class<? extends InputBoundary>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object> constructorObjects) {
        this.useCases = useCases;
        this.constructorClasses = constructorClasses;
        this.constructorObjects = constructorObjects;
    }

    public InputBoundary make(String useCase, Presenter presenter) {

        Class aClass = useCases.get(useCase);

        Constructor constructor = null;
        try {
            constructor = aClass.getConstructor(constructorClasses.get(useCase));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        InputBoundary newClass = null;
        try {
            newClass = (InputBoundary) constructor.newInstance(constructorObjects.get(useCase), presenter);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return newClass;
    }
}
