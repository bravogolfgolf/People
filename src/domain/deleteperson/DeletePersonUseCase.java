package domain.deleteperson;

import domain.Request;
import domain.UseCase;

public class DeletePersonUseCase implements UseCase {
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
