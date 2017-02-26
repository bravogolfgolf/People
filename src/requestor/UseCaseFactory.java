package requestor;

import responder.Presenter;

public interface UseCaseFactory {
    InputBoundary make(String useCase, Presenter presenter);
}
