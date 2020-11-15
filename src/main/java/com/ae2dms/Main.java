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
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
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
        MenuBarController.primaryStage = primaryStage;
        menu = new GameMenu();
        gameGrid = new GridPane();
        MenuBarController.gameGrid = gameGrid;
        GridPane root = new GridPane();
        root.add(menu, 0, 0);
        root.add(gameGrid, 0, 1);
        MenuBarController.primaryStage.setTitle(GameEngine.GAME_NAME);
        MenuBarController.primaryStage.setScene(new Scene(root));
        MenuBarController.primaryStage.show();
        MenuBarController.loadDefaultSaveFile(primaryStage);
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

}
