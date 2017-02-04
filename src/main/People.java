package main;

import domain.PersonRepository;
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

            PersonRepository repository = new PersonRepository();

            Interactor interactor = new Interactor();
            interactor.setRepository(repository);
            interactor.setPresenter(presenter);

            controller.setInteractor(interactor);
        });
    }
}