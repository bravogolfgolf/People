package ui;

import domain.UseCase;

public interface UseCaseFactory {
    UseCase make(String useCase);
}
