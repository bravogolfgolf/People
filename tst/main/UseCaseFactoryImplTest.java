package main;

import domain.AddPersonUseCase;
import domain.DeletePersonUseCase;
import domain.ExportUseCase;
import domain.ImportUseCase;
import org.junit.Test;
import ui.UseCaseFactory;

import static org.junit.Assert.assertTrue;

public class UseCaseFactoryImplTest {

    @Test
    public void makeMethodReturnsProperUseCase() {
        UseCaseFactory factory = new UseCaseFactoryImpl();
        assertTrue(factory.make("AddPersonUseCase") instanceof AddPersonUseCase);
        assertTrue(factory.make("DeletePersonUseCase") instanceof DeletePersonUseCase);
        assertTrue(factory.make("ExportUseCase") instanceof ExportUseCase);
        assertTrue(factory.make("ImportUseCase") instanceof ImportUseCase);
    }
}
