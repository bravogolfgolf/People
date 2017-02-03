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
    public void handle(Map<Integer, Person> response) {
        for (Person person : response.values()) {
            results.put(person.getId(), person);
        }
        mainFrame.updatePersonTableModel(results);
    }
}


