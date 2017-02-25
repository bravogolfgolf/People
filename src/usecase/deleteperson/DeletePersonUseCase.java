package usecase.deleteperson;

import databasegateway.DeletePersonGateway;
import requestor.InputBoundary;
import requestor.Request;

public class DeletePersonUseCase implements InputBoundary {
    private final DeletePersonGateway repository;
    private final InputBoundary refreshUseCase;

    public DeletePersonUseCase(DeletePersonGateway repository, InputBoundary refreshUseCase) {
        this.repository = repository;
        this.refreshUseCase = refreshUseCase;
    }

    @Override
    public void execute(Request request) {
        DeletePersonRequest r = (DeletePersonRequest) request;
        repository.deletePerson(r.id);
        refreshUseCase.execute(request);
    }
}
