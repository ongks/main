package seedu.oneline.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Toggle; 
import javafx.scene.control.ToggleButton; 
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.oneline.commons.core.Config;
import seedu.oneline.commons.core.GuiSettings;
import seedu.oneline.commons.events.ui.ExitAppRequestEvent;
import seedu.oneline.logic.Logic;
import seedu.oneline.model.UserPrefs;
import seedu.oneline.model.task.ReadOnlyTask;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart {

    private static final String ICON = "/images/oneline_32.png";
    private static final String FXML = "MainWindow.fxml";
    public static final int MIN_HEIGHT = 600;
    public static final int MIN_WIDTH = 450;

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private TaskPane taskPane;
    private TagListPanel tagListPanel;
    private ResultDisplay resultDisplay;
    private CommandBox commandBox;
    private Config config;
    private UserPrefs userPrefs;
    
    // Handles to elements of this Ui container
    private VBox rootLayout;
    private Scene scene;

    private String taskBookName;

    @FXML
    private AnchorPane taskPanePlaceholder;

    @FXML
    private AnchorPane commandBoxPlaceholder;

    @FXML
    private AnchorPane tagListPanelPlaceholder;

    @FXML
    private AnchorPane resultDisplayPlaceholder;
    
    @FXML
    private ToggleGroup tabGroup; 
    @FXML
    private ToggleButton allButton;
    @FXML
    private ToggleButton dayButton;
    @FXML
    private ToggleButton weekButton;
    @FXML
    private ToggleButton floatButton;


    public MainWindow() {
        super();
    }

    @Override
    public void setNode(Node node) {
        rootLayout = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    public static MainWindow load(Stage primaryStage, Config config, UserPrefs prefs, Logic logic) {

        MainWindow mainWindow = UiPartLoader.loadUiPart(primaryStage, new MainWindow());
        mainWindow.configure(config.getAppTitle(), config.getTaskBookName(), config, prefs, logic);
        return mainWindow;
    }

    private void configure(String appTitle, String taskBookName, Config config, UserPrefs prefs,
                           Logic logic) {

        //Set dependencies
        this.logic = logic;
        this.taskBookName = taskBookName;
        this.config = config;
        this.userPrefs = prefs;

        //Configure the UI
        setTitle(appTitle);
        setIcon(ICON);
        setWindowMinSize();
        setWindowDefaultSize(prefs);
        scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
    }

    void fillInnerParts() {
        taskPane = TaskPane.load(primaryStage, taskPanePlaceholder, logic.getFilteredTaskList(), logic.getTagColorMap());
        tagListPanel = TagListPanel.load(primaryStage, getTagListPlaceholder(), logic.getTagList(), logic.getTagColorMap());
        resultDisplay = ResultDisplay.load(primaryStage, getResultDisplayPlaceholder());
        commandBox = CommandBox.load(primaryStage, getCommandBoxPlaceholder(), resultDisplay, logic);
    }

    private AnchorPane getCommandBoxPlaceholder() {
        return commandBoxPlaceholder;
    }

    AnchorPane getResultDisplayPlaceholder() {
        return resultDisplayPlaceholder;
    }

    public AnchorPane getTagListPlaceholder() {
        return tagListPanelPlaceholder;
    }

    public void hide() {
        primaryStage.hide();
    }

    private void setTitle(String appTitle) {
        primaryStage.setTitle(appTitle);
    }

    /**
     * Sets the default size based on user preferences.
     */
    protected void setWindowDefaultSize(UserPrefs prefs) {
        primaryStage.setHeight(prefs.getGuiSettings().getWindowHeight());
        primaryStage.setWidth(prefs.getGuiSettings().getWindowWidth());
        if (prefs.getGuiSettings().getWindowCoordinates() != null) {
            primaryStage.setX(prefs.getGuiSettings().getWindowCoordinates().getX());
            primaryStage.setY(prefs.getGuiSettings().getWindowCoordinates().getY());
        }
    }

    private void setWindowMinSize() {
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
    }

    /**
     * Returns the current size and the position of the main Window.
     */
    public GuiSettings getCurrentGuiSetting() {
        return new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
    }

    @FXML
    public void handleHelp() {
        HelpWindow helpWindow = HelpWindow.load(primaryStage);
        helpWindow.show();
    }

    public void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        raise(new ExitAppRequestEvent());
    }
    
    public void postResult(String s) {
        resultDisplay.postMessage(s);
    }
    
    //@@author A0142605N 
    public TaskPane getTaskPane() {
        return this.taskPane; 
    }

    public TagListPanel getTagListPanel() {
        return this.tagListPanel;
    }
    
    
    // Button methods 
    public void resetAllButtons() {
        allButton.setSelected(false);
        dayButton.setSelected(false);
        weekButton.setSelected(false);
        floatButton.setSelected(false);
    }
    public ToggleButton getAllButton() {
        return allButton; 
    }
    public ToggleButton getDayButton() {
        return dayButton; 
    }
    public ToggleButton getWeekButton() {
        return weekButton; 
    }
    public ToggleButton getFloatButton() {
        return floatButton; 
    }
}
