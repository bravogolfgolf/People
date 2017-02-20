package ui;

import domain.DeletePersonRequest;
import domain.Request;
import domain.UseCase;
import main.RequestBuilderImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DeletePersonControllerTest implements UseCase {

    private DeletePersonRequest r;

    @Override
    public void execute(Request request) {
        this.r = (DeletePersonRequest) request;
    }

    private final RequestBuilder builder = new RequestBuilderImpl();
    private final UseCaseFactory factory = new UseCaseFactoryImplStub();
    private final Map<Integer, Object> args = new HashMap<>();
    private final int idToDelete = 1;

    @Before
    public void setUp() throws Exception {
        args.put(1, idToDelete);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new DeletePersonController(builder, args, factory);

        controller.execute();

        assertEquals(idToDelete, r.id);

    }

    class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public UseCase make(String useCase) {
            return DeletePersonControllerTest.this;
        }
    }
}


