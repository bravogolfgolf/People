package ui;

import domain.InputBoundary;

public interface UseCaseFactory {
    InputBoundary make(String useCase);
}
