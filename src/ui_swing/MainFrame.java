package ui_swing;

import builderfactory.ControllerFactory;
import com.apple.eawt.AppEvent;
import com.apple.eawt.Application;
import com.apple.eawt.FullScreenListener;
import com.apple.eawt.FullScreenUtilities;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import static java.awt.event.KeyEvent.VK_F;
import static java.awt.event.KeyEvent.VK_M;

@SuppressWarnings("WeakerAccess")
public class MainFrame extends JFrame implements Runnable {
    private static final String PERSON_DATABASE_FILE_EXTENSION = "per";
    private static final String PERSON_DATABASE_FILE_EXTENSION_DESC = "Person database files (*.per)";

    private static final String ENTER_FULL_SCREEN = "Enter Full Screen";
    private static final String HIDE_FORM = "Hide Form";
    private final ControllerFactory controllerFactory;

    private JMenu fileMenu;
    private JFileChooser fileChooser;
    private JMenu viewMenu;
    private JMenuItem fullScreenToggleMenuItem;
    private JMenu windowMenu;
    private JMenuItem minimizeMenuItem;
    private JMenuItem zoomMenuItem;

    private EntryPanel entryPanel;
    private PersonTablePanel personTablePanel;
    private StatusBar statusBar;

    private Preferences preferences;

    // MacOS Specific
    private Application application;
    private PreferenceDialog preferenceDialog;

    public MainFrame(ControllerFactory controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    @Override
    public void run() {

        // MacOS Specific
        application = Application.getApplication();
        macOSPreferencesMenuHandling();
        macOSFullScreenHandling();

        //Application Specific
        setupMainFrame();
        createAndAddComponentsToMainFrame();
        updatePersonTablePanelModel();
        setMainFrameVisible();
    }

    private void macOSPreferencesMenuHandling() {
        application.setPreferencesHandler(preferencesEvent -> {
            preferences = Preferences.userRoot().node("database");
            String username = preferences.get("username", "");
            String password = preferences.get("password", "");
            int portNumber = preferences.getInt("portNumber", 3306);
            preferenceDialog.setDefaults(username, password, portNumber);
            preferenceDialog.setVisible(true);
        });
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
        setMinimumSize(new Dimension(850, 340));
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
        createAndSetMenuBar();
        createAndAddEntryPane();
        createAndAddPersonTablePanel();
        createAndAddStatusBar();
        createPreferenceDialog();
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
            if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                Map<Integer, Object> args = new HashMap<>();
                args.put(0, fileChooser.getSelectedFile());
                String string = tryExport(args);
                statusBar.setStatusLabel(string);
            }
        });
        fileMenu.add(exportDataMenuItem);
    }

    private JMenuItem newJMenuItemWithListener(String text, ActionListener actionListener) {
        final JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    private String tryExport(Map<Integer, Object> args) {
        String string = null;
        try {
            string = (String) controllerFactory.make("Export", args, new ExportPresenter(new ExportView())).execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MainFrame.this, "Could not Export file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return string;
    }

    private void addImportMenuItem() {
        final JMenuItem importDataMenuItem = newJMenuItemWithListener("Import Data...", e -> {
            if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                Map<Integer, Object> args = new HashMap<>();
                args.put(0, fileChooser.getSelectedFile());
                String string = tryImport(args);
                statusBar.setStatusLabel(string);

                updatePersonTablePanelModel();
            }
        });
        fileMenu.add(importDataMenuItem);
    }

    private String tryImport(Map<Integer, Object> args) {
        String string = null;
        try {
            string = (String) controllerFactory.make("Import", args, new ImportPresenter(new ImportView())).execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MainFrame.this, "Could not import file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return string;
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
        fullScreenToggleMenuItem.setAccelerator(KeyStroke.getKeyStroke(VK_F, InputEvent.CTRL_DOWN_MASK | InputEvent.META_DOWN_MASK));
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
        minimizeMenuItem.setAccelerator(KeyStroke.getKeyStroke(VK_M, InputEvent.META_DOWN_MASK));
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
        entryPanel = new EntryPanel(new EntryPanelListener() {
            @Override
            public void addEventEmitted(EntryPanelAddEvent e) {
                Map<Integer, Object> args = new HashMap<>();
                args.put(0, e.fullName);
                args.put(1, e.occupation);
                args.put(2, e.ageCategory);
                args.put(3, e.employmentStatus);
                args.put(4, e.uSCitizen);
                args.put(5, e.taxId);
                args.put(6, e.gender);
                String string = (String) controllerFactory.make("AddPerson", args, new AddPersonPresenter(new AddPersonView())).execute();
                statusBar.setStatusLabel(string);

                MainFrame.this.updatePersonTablePanelModel();
            }

            @Override
            public void updateEventEmitted(EntryPanelUpdateEvent e) {
                Map<Integer, Object> args = new HashMap<>();
                args.put(0, e.id);
                args.put(1, e.fullName);
                args.put(2, e.occupation);
                args.put(3, e.ageCategory);
                args.put(4, e.employmentStatus);
                args.put(5, e.uSCitizen);
                args.put(6, e.taxId);
                args.put(7, e.gender);
                String string = (String) controllerFactory.make("UpdatePerson", args, new UpdatePersonPresenter(new UpdatePersonView())).execute();
                statusBar.setStatusLabel(string);

                MainFrame.this.updatePersonTablePanelModel();
            }

            @Override
            public void deleteEventEmitted(EntryPanelDeleteEvent e) {
                Map<Integer, Object> args = new HashMap<>();
                args.put(0, e.id);
                String string = (String) controllerFactory.make("DeletePerson", args, new DeletePersonPresenter(new DeletePersonView())).execute();
                statusBar.setStatusLabel(string);

                MainFrame.this.updatePersonTablePanelModel();
            }
        });
        add(entryPanel, BorderLayout.LINE_START);
    }

    private void createAndAddPersonTablePanel() {
        personTablePanel = new PersonTablePanel(entryPanel::rowSelected);
        add(personTablePanel, BorderLayout.CENTER);
    }

    private void createAndAddStatusBar() {
        statusBar = new StatusBar();
        add(statusBar, BorderLayout.PAGE_END);
    }

    private void createPreferenceDialog() {
        preferenceDialog = new PreferenceDialog(this, (username, password, portNumber) -> {
            preferences.put("username", username);
            preferences.put("password", password);
            preferences.putInt("portNumber", portNumber);
            preferenceDialog.setVisible(false);
        });
    }

    private void updatePersonTablePanelModel() {
        PersonTableModelRecord[] records = (PersonTableModelRecord[]) controllerFactory.make("Refresh", new HashMap<>(), new RefreshPresenter(new RefreshView())).execute();
        personTablePanel.updateModel(records);
    }

    private void setMainFrameVisible() {
        setVisible(true);
    }
}