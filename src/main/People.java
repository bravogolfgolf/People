package main;

import data.PersonRepositoryMySQL;
import data.RepositoryInteractor;
import domain.ExportImport;
import ui.PresenterImpl;
import ui.MainFrame;

import javax.swing.*;

class People {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {

            MainFrame mainFrame = new MainFrame();
            PresenterImpl presenter = new PresenterImpl(mainFrame);
            RepositoryInteractor repository = new PersonRepositoryMySQL();
            ExportImport exportImport = new ExportImport();
            RequestBuilderImpl builder = new RequestBuilderImpl();
            UseCaseFactoryImpl useCaseFactory = new UseCaseFactoryImpl(repository, exportImport, presenter);
            ControllerFactoryImpl controllerFactory = new ControllerFactoryImpl(builder, useCaseFactory);
            mainFrame.initialize(controllerFactory);
        });
    }
}