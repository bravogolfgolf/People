package main;

import contoller.ControllerFactoryImpl;
import database.PersonRepository;
import database.PersonRepositoryMySQL;
import usecase.RequestBuilderImpl;
import usecase.UseCaseFactoryImpl;
import view.MainFrame;

import javax.swing.*;

class People {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> {

            PersonRepository repository = new PersonRepositoryMySQL();
            RequestBuilderImpl requestBuilder = new RequestBuilderImpl();
            UseCaseFactoryImpl useCaseFactory = new UseCaseFactoryImpl(repository);
            ControllerFactoryImpl controllerFactory = new ControllerFactoryImpl(requestBuilder, useCaseFactory);
            MainFrame mainFrame = new MainFrame(controllerFactory);
            mainFrame.initialize();
        });
    }
}