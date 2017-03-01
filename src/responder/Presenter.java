package responder;

public interface Presenter {
    void present(RefreshResponse response);

    Object getViewModel();
}
