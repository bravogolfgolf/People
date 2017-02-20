package main;

import data.PersonRepositoryMySQL;
import domain.ExportImport;
import domain.Presenter;
import ui.MainFrame;

import javax.swing.*;

class People {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {

            MainFrame mainFrame = new MainFrame();
            Presenter presenter = new Presenter(mainFrame);
            PersonRepositoryMySQL repository = new PersonRepositoryMySQL();
            ExportImport exportImport = new ExportImport();
            RequestBuilderImpl builder = new RequestBuilderImpl();
            UseCaseFactoryImpl useCaseFactory = new UseCaseFactoryImpl(repository, exportImport, presenter);
            ControllerFactoryImpl controllerFactory = new ControllerFactoryImpl(builder, useCaseFactory);
            mainFrame.initialize(controllerFactory);
        });
    }
}