package contoller;

import requestor.InputBoundary;
import respondor.Presenter;

public interface UseCaseFactory {
    InputBoundary make(String useCase, Presenter presenter);
}
