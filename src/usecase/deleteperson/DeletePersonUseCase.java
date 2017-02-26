package usecase.deleteperson;

import databasegateway.DeletePersonGateway;
import requestor.InputBoundary;
import requestor.Request;
import responder.Presenter;

public class DeletePersonUseCase implements InputBoundary {
    private final DeletePersonGateway repository;

    public DeletePersonUseCase(DeletePersonGateway repository, Presenter presenter) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request) {
        DeletePersonRequest r = (DeletePersonRequest) request;
        repository.deletePerson(r.id);
    }
}
