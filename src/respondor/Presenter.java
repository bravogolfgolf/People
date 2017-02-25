package respondor;

public interface Presenter {
    void present(Response response);

    PersonRecord[] getViewModel();
}
