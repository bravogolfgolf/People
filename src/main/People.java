package main;

import data.PersonRepositoryMySQL;
import data.RepositoryInteractor;
import domain.ExportImport;
import ui.MainFrame;
import ui.PresenterImpl;

import javax.swing.*;

class People {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {

            MainFrame mainFrame = new MainFrame();
            PresenterImpl presenter = new PresenterImpl(mainFrame);
            RepositoryInteractor repository = new PersonRepositoryMySQL();
            ExportImport exportImport = new ExportImport();
            RequestBuilderImpl requestBuilder = new RequestBuilderImpl();
            ResponseBuilderImpl responseBuilder = new ResponseBuilderImpl();
            UseCaseFactoryImpl useCaseFactory = new UseCaseFactoryImpl(repository, exportImport, responseBuilder, presenter);
            ControllerFactoryImpl controllerFactory = new ControllerFactoryImpl(requestBuilder, useCaseFactory);
            mainFrame.initialize(controllerFactory);
        });
    }
}