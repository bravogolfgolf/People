package responder;

public interface RefreshResponder {
    void present(RefreshResponse response);

    Object getViewModel();
}
