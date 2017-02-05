package ui;

import com.apple.eawt.AppEvent;
import com.apple.eawt.Application;
import com.apple.eawt.FullScreenListener;
import com.apple.eawt.FullScreenUtilities;
import domain.ControllerMainFrame;
import domain.MainFramePresenter;
import domain.PersonMessage;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.awt.event.KeyEvent.VK_F;
import static java.awt.event.KeyEvent.VK_M;

public class MainFrame extends JFrame implements MainFramePresenter {

    private static final String PERSON_DATABASE_FILE_EXTENSION = "per";
    private static final String PERSON_DATABASE_FILE_EXTENSION_DESC = "Person database files (*.per)";

    static final String NEW_LINE = System.lineSeparator();

    private static final String ENTER_FULL_SCREEN = "Enter Full Screen";
    private static final String HIDE_FORM = "Hide Form";
    //    private ToolBar toolBar;
    private final JMenuBar menuBar = new JMenuBar();
    private FormPanel formPanel;
    private final PersonTablePanel personTablePanel = new PersonTablePanel();
    //    private final TextPanel textPanel = new TextPanel();
    private JMenuItem minimizeMenuItem;
    private JMenuItem zoomMenuItem;
    private JMenuItem fullScreenToggleMenuItem;
    private ControllerMainFrame controller;

    public MainFrame() {
        super();
        setup();
        addListeners();
        addComponents();
    }

    public void setController(ControllerMainFrame controller) {
        this.controller = controller;
    }

    @Override
    public void updatePersonTableModel(PersonMessage[] response) {
        personTablePanel.addDataForPersonTableModel(response);
    }

    private void setup() {
        createMenuBar();
        setupMainFrame();
    }

    private void createMenuBar() {
        final JMenu fileMenu = createFileMenu();
        final JMenu viewMenu = createViewMenu();
        final JMenu windowMenu = createWindowMenu();
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(windowMenu);
    }

    private JMenu createFileMenu() {

        final JMenu fileMenu = new JMenu("File");


        final JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter(PERSON_DATABASE_FILE_EXTENSION_DESC, PERSON_DATABASE_FILE_EXTENSION);
        fileChooser.addChoosableFileFilter(filter);
        final JMenuItem exportDataMenuItem = newJMenuItemWithListener("Export Data...", e -> {
            if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                try {
                    controller.exportRepository(fileChooser.getSelectedFile());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Could not export file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        });

        final JMenuItem importDataMenuItem = newJMenuItemWithListener("Import Data...", e -> {
            if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                try {
                    controller.loadRepository(fileChooser.getSelectedFile());
                    personTablePanel.refresh();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Could not import file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        });

        fileMenu.add(exportDataMenuItem);
        fileMenu.add(importDataMenuItem);
        return fileMenu;
    }

    private JMenuItem newJMenuItemWithListener(String text, ActionListener actionListener) {
        final JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    private JMenu createViewMenu() {
        JMenu viewMenu = new JMenu("View");

        final JMenuItem formToggleMenuItem = new JMenuItem(HIDE_FORM);
        formToggleMenuItem.addActionListener(e -> {
            togglePanel();
            formToggleMenuItem.setText(toggleText());
        });

        fullScreenToggleMenuItem = new JMenuItem(ENTER_FULL_SCREEN);
        fullScreenToggleMenuItem.setAccelerator(KeyStroke.getKeyStroke(VK_F, ActionEvent.CTRL_MASK | ActionEvent.META_MASK));
        fullScreenToggleMenuItem.addActionListener(e -> Application.getApplication().requestToggleFullScreen(MainFrame.this));

        viewMenu.add(formToggleMenuItem);
        viewMenu.addSeparator();
        viewMenu.add(fullScreenToggleMenuItem);
        return viewMenu;
    }

    private void togglePanel() {
        formPanel.setVisible(!formPanel.isVisible());
    }

    private String toggleText() {
        if (formPanel.isVisible())
            return HIDE_FORM;
        return "Show Form";
    }

    private JMenu createWindowMenu() {
        JMenu windowMenu = new JMenu("Window");

        minimizeMenuItem = new JMenuItem("Minimize");
        minimizeMenuItem.setAccelerator(KeyStroke.getKeyStroke(VK_M, ActionEvent.META_MASK));
        minimizeMenuItem.addActionListener(e -> setState(JFrame.ICONIFIED));

        zoomMenuItem = new JMenuItem("Zoom");
        zoomMenuItem.addActionListener(e -> setExtendedState((getExtendedState() != MAXIMIZED_BOTH) ? MAXIMIZED_BOTH : NORMAL));

        windowMenu.add(minimizeMenuItem);
        windowMenu.add(zoomMenuItem);
        return windowMenu;
    }

    private void setupMainFrame() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(850, 310));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        FullScreenUtilities.setWindowCanFullScreen(this, true);
        setVisible(true);
    }

    private void addListeners() {
        addFullScreenListener();
        addWindowStateListener();
        addFormPanelListener();
//        addToolBarListener();
    }

    private void addFullScreenListener() {
        FullScreenUtilities.addFullScreenListenerTo(this, new FullScreenListener() {
            @Override
            public void windowEnteringFullScreen(AppEvent.FullScreenEvent fullScreenEvent) {
            }

            @Override
            public void windowEnteredFullScreen(AppEvent.FullScreenEvent fullScreenEvent) {
                disableMenuItems();
                fullScreenToggleMenuItem.setText("Exit Full Screen");
            }

            @Override
            public void windowExitingFullScreen(AppEvent.FullScreenEvent fullScreenEvent) {
            }

            @Override
            public void windowExitedFullScreen(AppEvent.FullScreenEvent fullScreenEvent) {
                enableMenuItems();
                fullScreenToggleMenuItem.setText(ENTER_FULL_SCREEN);
            }
        });
    }

    private void disableMenuItems() {
        minimizeMenuItem.setEnabled(false);
        zoomMenuItem.setEnabled(false);
    }

    private void enableMenuItems() {
        minimizeMenuItem.setEnabled(true);
        zoomMenuItem.setEnabled(true);
    }

    private void addWindowStateListener() {
        addWindowStateListener(e -> {
            if (e.getNewState() != JFrame.ICONIFIED) {
                fullScreenToggleMenuItem.setEnabled(true);
                enableMenuItems();
            } else {
                fullScreenToggleMenuItem.setEnabled(false);
                disableMenuItems();
            }
        });
    }

    private void addFormPanelListener() {
        formPanel = new FormPanel(formEvent -> {
            controller.addPerson(formEvent);
            personTablePanel.refresh();
        });
    }

    private void addToolBarListener() {
//        toolBar = new ToolBar(textPanel::appendText);
    }

    private void addComponents() {
        add(personTablePanel, BorderLayout.CENTER);
//        add(textPanel, BorderLayout.CENTER);
//        add(toolBar, BorderLayout.PAGE_START);
        add(formPanel, BorderLayout.LINE_START);
        SwingUtilities.getRootPane(formPanel.okButton).setDefaultButton(formPanel.okButton);

    }
}