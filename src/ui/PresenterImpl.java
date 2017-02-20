package ui;

import domain.Presenter;
import domain.RefreshResponse;

public class PresenterImpl implements Presenter {

    private final MainFramePresenter mainFrame;

    public PresenterImpl(MainFramePresenter mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void present(RefreshResponse[] responses) {
        mainFrame.update(responses);
    }
}


