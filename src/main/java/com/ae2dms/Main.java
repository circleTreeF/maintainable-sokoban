package com.ae2dms;

import com.ae2dms.controller.MenuBarController;
import com.ae2dms.model.GameEngine;
import com.ae2dms.view.DialogWindow;
import com.ae2dms.view.GameMenu;
import com.ae2dms.view.GraphicObject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.
 *
 * @description: This is the main class
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/9 21:10 given
 * @version: 1.0
 */
public class Main extends Application {
    private Stage primaryStage;
    private GameEngine gameEngine;
    private GridPane gameGrid;
    private File saveFile;
    private MenuBar menu;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage
     * @return void
     * @description: set the to menu of the initial interface of the game
     * In the menu, 3 options are "file", "level", "about"
     * * Under each option, there are menu items.
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:11 given
     * @version: 1.0.0
     **/

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        menu = new GameMenu();
        gameGrid = new GridPane();
        MenuBarController.gameGrid = gameGrid;
        GridPane root = new GridPane();
        root.add(menu, 0, 0);
        root.add(gameGrid, 0, 1);
        primaryStage.setTitle(GameEngine.GAME_NAME);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        loadDefaultSaveFile(primaryStage);
    }

    /**
     * @param primaryStage
     * @return void
     * @description load default game map file i.e. level/SampleGame.skb
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:11 given
     * @version: 1.0.0
     **/

    void loadDefaultSaveFile(Stage primaryStage) {
        this.primaryStage = primaryStage;
        MenuBarController.primaryStage = primaryStage;
        InputStream defaultInputStream = getClass().getClassLoader().getResourceAsStream("level/SampleGame.skb");
        initializeGame(defaultInputStream);
        setEventFilter();
    }

    /**
     * @param inputGameFile
     *         the file stream of the file which stores the map of the game, may be default file/user specified file in the getExtensionFilters
     * @return void
     * @description For new game starting, initialize the game to start
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 21:50 given
     * @version: 1.0.0
     **/

    private void initializeGame(InputStream inputGameFile) {

        gameEngine = new GameEngine(inputGameFile, true);
        MenuBarController.gameEngine = gameEngine;
        reloadGrid();
    }

    /**
     * @param
     * @return void
     * @description: add the event filter about the keyboard pressing at the primary stage
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:12 given
     * @version: 1.0.0
     **/


    private void setEventFilter() {
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            //TODO: compose to the single method
            gameEngine.handleKey(event.getCode());
            reloadGrid();
        });
    }

    /**
     * @return
     * @throw FileNotFoundException
     * @description: load the user defined map file
     * <p>
     * if there is the saved file, then use this file to continues the game
     * otherwise, initialize game with the default game file
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:12 given
     * @version: 1.0.0
     */
    private void loadGameFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
        saveFile = fileChooser.showOpenDialog(primaryStage);

        //TODO: refactor the if statement
        if (saveFile != null) {
            if (GameEngine.isDebugActive()) {
                GameEngine.logger.info("Loading save file: " + saveFile.getName());
            }
            initializeGame(new FileInputStream(saveFile));
        }
    }

    /**
     * @param
     * @return void
     * @description when the gameEngine indicates the game is completed, show victory message
     * otherwise, get the current level map, ...
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 22:24 given
     * @version: 1.0.0
     **/


    private void reloadGrid() {
        //TODO: refactor the if statement
        if (gameEngine.isGameComplete()) {
            showVictoryMessage();
            return;
        }

        Level currentLevel = gameEngine.getCurrentLevel();
        Level.LevelIterator levelGridIterator = (Level.LevelIterator) currentLevel.iterator();
        gameGrid.getChildren().clear();
        while (levelGridIterator.hasNext()) {
            addObjectToGrid(levelGridIterator.next(), levelGridIterator.getCurrentPosition());
        }
        gameGrid.autosize();
        primaryStage.sizeToScene();
    }

    /**
     * @param
     * @return void
     * @description present the victory information and date about this play
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 21:48 given
     * @version: 1.0.0
     **/


    private void showVictoryMessage() {
        String dialogTitle = "Game Over!";
        String dialogMessage = "You completed " + gameEngine.mapSetName + " in " + gameEngine.movesCount + " moves!";
        MotionBlur motionBlur = new MotionBlur(2, 3);

        DialogWindow messageWindow = new DialogWindow(primaryStage, dialogTitle, dialogMessage, motionBlur);
        messageWindow.show();
    }

    /**
     * @param gameObject
     * @param location
     * @return void
     * @description //TODO
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 21:42 given
     * @version: 1.0.0
     **/


    private void addObjectToGrid(GameObject gameObject, Point location) {
        GraphicObject graphicObject = new GraphicObject(gameObject);
        gameGrid.add(graphicObject, location.y, location.x);
    }

    /**
     * @param
     * @return void
     * @description close the game, i.e. exist from the whole program
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 22:25 given
     * @version: 1.0.0
     **/

    public void closeGame() {
        System.exit(0);
    }

    //TODO: this feature remains to be implemented
    public void saveGame() {
    }

    /**
     * @param
     * @return void
     * @description to load the game file
     * TODO: FileNotFoundException in this method. This could be refactored (according to class 6)
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 22:25 given
     * @version: 1.0.0
     **/

    public void loadGame() {
        try {
            loadGameFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO: this feature is not implemented temporarily replaced by existing from the game
    public void undo() {
        closeGame();
    }

    //TODO: reset this level to the initial scene
    public void resetLevel() {
    }

    /**
     * @param
     * @return void
     * @description: to show the basic information about this app
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 21:57 given
     * @version: 1.0.0
     **/
    /**
     * @param
     * @return void
     * @description: to show the basic information about this app
     * refactored to extract new class
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 21:57 given
     * @version: 1.0.1
     **/


    public void showAbout() {
        String title = "About this game";
        String message = "Game created by Yizirui FANG 20127901\n";

        DialogWindow aboutWindow = new DialogWindow(primaryStage, title, message, null);
        aboutWindow.show();
    }

    /**
     * play the default music when this menu item is clicked
     */

    public void toggleMusic() {
        // TODO
    }

    /**
     * @param
     * @return void
     * @description: to start the debug mode of the game. In this mode, every edge of the block would be highlighted
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:14 given
     * @version: 1.0.0
     **/


    public void toggleDebug() {
        gameEngine.toggleDebug();
        reloadGrid();
    }
}
