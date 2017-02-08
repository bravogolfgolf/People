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

    //Menu Bar Components
    private JMenu fileMenu;
    private JFileChooser fileChooser;
    private JMenu viewMenu;
    private JMenuItem fullScreenToggleMenuItem;
    private JMenu windowMenu;
    private JMenuItem minimizeMenuItem;
    private JMenuItem zoomMenuItem;

    private EntryPanel entryPanel;
    private PersonTablePanel personTablePanel;

    // Delete if never used by end of tutorial
    private TextPanel textPanel;

    // MacOS Specific
    private final Application application;
    private PreferenceDialog preferenceDialog;

    //Application Specific
    private ControllerMainFrame controller;

    public MainFrame() {
        super();

        // MacOS Specific
        application = Application.getApplication();
        macOSPreferencesMenuHandling();
        macOSFullScreenHandling();

        //Application Specific
        setupMainFrame();
        createAndAddComponentsToMainFrame();
        setMainFrameVisible();
    }

    public void setController(ControllerMainFrame controller) {
        this.controller = controller;
    }

    @Override
    public void updatePersonTableModel(PersonMessage[] response) {
        personTablePanel.addDataForPersonTableModel(response);
    }

    private void macOSPreferencesMenuHandling() {
        application.setPreferencesHandler(preferencesEvent -> preferenceDialog.setVisible(true));

    }

    private void macOSFullScreenHandling() {
        FullScreenUtilities.setWindowCanFullScreen(this, true);
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

    private void setupMainFrame() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(850, 310));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    private void createAndAddComponentsToMainFrame() {
        // Delete if never used by end of tutorial
        createAndAddTextPanel();
        createAndAddToolBar();

        //Major Form Components
        createAndSetMenuBar();
        createAndAddEntryPane();
        createAndAddPersonTablePanel();
        createPreferenceDialog();
    }

    private void createAndAddTextPanel() {
        textPanel = new TextPanel();
        textPanel.setVisible(false);
        add(textPanel, BorderLayout.CENTER);

    }

    private void createAndAddToolBar() {
        ToolBar toolBar = new ToolBar(textPanel::appendText);
        toolBar.setVisible(false);
        add(toolBar, BorderLayout.PAGE_START);
    }

    private void createAndSetMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        fileMenu = createFileMenu();
        viewMenu = createViewMenu();
        windowMenu = createWindowMenu();
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(windowMenu);
        setJMenuBar(menuBar);
    }

    private JMenu createFileMenu() {
        fileMenu = new JMenu("File");
        addFileFilterToFileChooser();
        addExportMenuItem();
        addImportMenuItem();
        return fileMenu;
    }

    private void addFileFilterToFileChooser() {
        fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter(PERSON_DATABASE_FILE_EXTENSION_DESC, PERSON_DATABASE_FILE_EXTENSION);
        fileChooser.addChoosableFileFilter(filter);
    }

    private void addExportMenuItem() {
        final JMenuItem exportDataMenuItem = newJMenuItemWithListener("Export Data...", e -> {
            if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                try {
                    controller.exportRepository(fileChooser.getSelectedFile());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Could not export file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        });
        fileMenu.add(exportDataMenuItem);
    }

    private JMenuItem newJMenuItemWithListener(String text, ActionListener actionListener) {
        final JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    private void addImportMenuItem() {
        final JMenuItem importDataMenuItem = newJMenuItemWithListener("Import Data...", e -> {
            if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                try {
                    controller.loadRepository(fileChooser.getSelectedFile());
                    personTablePanel.refresh();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Could not import file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        });
        fileMenu.add(importDataMenuItem);
    }

    private JMenu createViewMenu() {
        viewMenu = new JMenu("View");
        addEntryFormToggleMenuItem();
        addMenuItemSeparator();
        addFullScreenToggleMenuItem();

        return viewMenu;
    }

    private void addEntryFormToggleMenuItem() {
        final JMenuItem entryFormToggleMenuItem = new JMenuItem(HIDE_FORM);
        entryFormToggleMenuItem.addActionListener(e -> {
            toggleEntryPanel();
            entryFormToggleMenuItem.setText(toggleTextOfEntryFormToggleMenuItem());
        });
        viewMenu.add(entryFormToggleMenuItem);
    }

    private void toggleEntryPanel() {
        entryPanel.setVisible(!entryPanel.isVisible());
    }

    private String toggleTextOfEntryFormToggleMenuItem() {
        return entryPanel.isVisible() ? HIDE_FORM : "Show Form";
    }

    private void addMenuItemSeparator() {
        viewMenu.addSeparator();
    }

    private void addFullScreenToggleMenuItem() {
        fullScreenToggleMenuItem = new JMenuItem(ENTER_FULL_SCREEN);
        fullScreenToggleMenuItem.setAccelerator(KeyStroke.getKeyStroke(VK_F, ActionEvent.CTRL_MASK | ActionEvent.META_MASK));
        fullScreenToggleMenuItem.addActionListener(e -> application.requestToggleFullScreen(MainFrame.this));
        viewMenu.add(fullScreenToggleMenuItem);
    }

    private JMenu createWindowMenu() {
        windowMenu = new JMenu("Window");
        addMinimizeMenuItem();
        addZoomMenuItem();
        return windowMenu;
    }

    private void addZoomMenuItem() {
        zoomMenuItem = new JMenuItem("Zoom");
        zoomMenuItem.addActionListener(e -> setExtendedState((getExtendedState() != MAXIMIZED_BOTH) ? MAXIMIZED_BOTH : NORMAL));
        windowMenu.add(zoomMenuItem);
    }

    private void addMinimizeMenuItem() {
        minimizeMenuItem = new JMenuItem("Minimize");
        minimizeMenuItem.setAccelerator(KeyStroke.getKeyStroke(VK_M, ActionEvent.META_MASK));
        minimizeMenuItem.addActionListener(e -> setState(JFrame.ICONIFIED));
        windowMenu.add(minimizeMenuItem);
    }

    private void disableMenuItems() {
        minimizeMenuItem.setEnabled(false);
        zoomMenuItem.setEnabled(false);
    }

    private void enableMenuItems() {
        minimizeMenuItem.setEnabled(true);
        zoomMenuItem.setEnabled(true);
    }

    private void createAndAddEntryPane() {
        entryPanel = new EntryPanel(formEvent -> {
            controller.addPerson(formEvent);
            personTablePanel.refresh();
        });
        add(entryPanel, BorderLayout.LINE_START);
        SwingUtilities.getRootPane(entryPanel.okButton).setDefaultButton(entryPanel.okButton);
    }

    private void createAndAddPersonTablePanel() {
        personTablePanel = new PersonTablePanel(id -> {
            controller.deletePerson(id);
            personTablePanel.refresh();
        });
        add(personTablePanel, BorderLayout.CENTER);
    }

    private void createPreferenceDialog() {
        preferenceDialog = new PreferenceDialog((userName, password, port) -> {
            ;
        });
    }

    private void setMainFrameVisible() {
        setVisible(true);
    }
}