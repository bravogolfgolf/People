package builderfactory;

import responder.RefreshResponder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class UseCaseFactory {

    private final Map<String, Class> useCases;
    private final Map<String, Class[]> constructorClasses;
    private final Map<String, Object[]> constructorObjects;

    public UseCaseFactory(Map<String, Class> useCases, Map<String, Class[]> constructorClasses, Map<String, Object[]> constructorObjects) {
        this.useCases = useCases;
        this.constructorClasses = constructorClasses;
        this.constructorObjects = constructorObjects;
    }

    public UseCase make(String useCase, RefreshResponder presenter) {

        Class<?> aClass = useCases.get(useCase);

        Constructor constructor = null;
        try {
            constructor = aClass.getConstructor(constructorClasses.get(useCase));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        Object[] objects = new Object[constructorObjects.get(useCase).length + 1];
        int i = 0;
        for (Object object : constructorObjects.get(useCase)) {
            objects[i++] = object;
        }
        objects[i] = presenter;

        Object newClass = null;
        try {
            assert constructor != null;
            newClass = constructor.newInstance(objects);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return (UseCase) newClass;
    }
}
