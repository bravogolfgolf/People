package usecase;

import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import responder.DeletePersonResponder;
import responder.DeletePersonResponse;

public class DeletePersonUseCase extends UseCase {
    private final PersonRepository repository;
    private final DeletePersonResponder responder;

    public DeletePersonUseCase(PersonRepository repository, DeletePersonResponder responder) {
        this.repository = repository;
        this.responder = responder;
    }

    @Override
    public void execute(Request request) {
        DeletePersonRequest r = (DeletePersonRequest) request;
        repository.deletePerson(r.id);
        DeletePersonResponse response = new DeletePersonUseCaseResponse();
        response.setId(r.id);
        responder.present(response);
    }
}
