package responder;

public interface DeletePersonResponder {
    void present(DeletePersonResponse response);

    Object generateView();
}
