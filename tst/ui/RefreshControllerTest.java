package ui;

import domain.RefreshRequest;
import domain.Request;
import domain.UseCase;
import main.RequestBuilderImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RefreshControllerTest implements UseCase {

    private RefreshRequest r;

    @Override
    public void execute(Request request) {
        this.r = (RefreshRequest) request;
    }

    private final RequestBuilder builder = new RequestBuilderImpl();
    private final UseCaseFactory factory = new RefreshControllerTest.UseCaseFactoryImplStub();
    private final Map<Integer, Object> args = new HashMap<>();

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new RefreshController(builder, args, factory);

        controller.execute();

        assertTrue(r != null);
    }

    class UseCaseFactoryImplStub implements UseCaseFactory {
        @Override
        public UseCase make(String useCase) {
            return RefreshControllerTest.this;
        }
    }
}

