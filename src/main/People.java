package main;

import data.Database;
import domain.Interactor;
import domain.Presenter;
import domain.Controller;
import ui.MainFrame;

import javax.swing.*;

class People {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {

            Controller controller = new Controller();

            MainFrame mainFrame = new MainFrame();
            mainFrame.setController(controller);

            Presenter presenter = new Presenter();
            presenter.setMainFrame(mainFrame);

            Database database = new Database();

            Interactor interactor = new Interactor();
            interactor.setDatabase(database);
            interactor.setPresenter(presenter);

            controller.setInteractor(interactor);
        });
    }
}