package responder;

public interface Presenter {
    void present(RefreshResponse response);

    RefreshViewModel[] getViewModel();
}
