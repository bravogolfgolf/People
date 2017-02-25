package respondor;

import view.PersonRecord;

public interface Presenter {
    void present(Response response);

    PersonRecord[] getViewModel();
}
