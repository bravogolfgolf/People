package usecase;

import responder.DeletePersonResponse;

public class DeletePersonUseCaseResponse implements DeletePersonResponse {

    private int id;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
