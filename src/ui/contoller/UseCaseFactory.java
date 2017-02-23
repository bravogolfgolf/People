package ui.contoller;

import domain.InputBoundary;
import domain.Presenter;

public interface UseCaseFactory {
    InputBoundary make(String useCase, Presenter presenter);
}
