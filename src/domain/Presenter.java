package domain;

import java.util.HashMap;
import java.util.Map;

public class Presenter implements PresenterInteractor {

    private final Map<Integer, Person> results = new HashMap<>();
    private MainFramePresenter mainFrame;

    public void setMainFrame(MainFramePresenter mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void handle(Response response) {
        for (Person person : response.people.values()) {
            results.put(person.getId(), person);
        }
        mainFrame.updatePersonTableModel(results);
    }
}


