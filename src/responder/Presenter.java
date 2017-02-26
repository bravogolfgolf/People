package responder;

public interface Presenter {
    void present(Response response);

    PersonRecord[] getViewModel();
}
