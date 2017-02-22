package domain.deleteperson;

import domain.Request;
import domain.InputBoundary;

public class DeletePersonUseCase implements InputBoundary {
    private final DeletePersonGateway repository;

    public DeletePersonUseCase(DeletePersonGateway repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request) {
        DeletePersonRequest r = (DeletePersonRequest) request;
        repository.deletePerson(r.id);
    }
}
