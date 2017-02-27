package usecase.deleteperson;

import databasegateway.DeletePersonGateway;
import requestor.Request;
import requestor.UseCase;
import responder.Presenter;

public class DeletePersonUseCase extends UseCase {
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
