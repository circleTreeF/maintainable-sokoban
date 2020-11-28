package com.ae2dms.controller;


import com.ae2dms.GameObject;
import com.ae2dms.model.GameEngine;
import com.ae2dms.model.Level;
import com.ae2dms.view.DialogWindow;
import com.ae2dms.view.GraphicObjectFactory;
import javafx.fxml.FXML;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.*;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @className: MenuBarController
 * @description: This class includes controller for the GamePage.fxml, which is the page of playing this game
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:57
 */
public class GamePageController {
    public static Stage primaryStage;
    public static GameEngine gameEngine;
    @FXML
    private GridPane gameGrid;
    MusicPlayer musicPlayer;

    /**
     * @param
     * @return void
     * @description: initialize the game page element, gameGrid. Display the initial level of the map to the game page
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 13:23
     * @version: 1.0.0
     **/


    public void initialize() {
        String defaultMusic = "puzzle_theme.wav";
        primaryStage.show();
        loadDefaultSaveFile(primaryStage);
        musicPlayer = new MusicPlayer(defaultMusic);
    }

    /**
     * @param
     * @return void
     * @description: save the current game level to file
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * extracted from Main.saveGame()
     * @date: 2020/11/15 15:11
     * @version: 1.0.0
     **/

    public void saveGame() throws IOException {
        OperateFile operateFile = new OperateFile();
        File savedLocation = operateFile.selectSaveGamePath(primaryStage);
        gameEngine.saveGame(savedLocation);
    }

    /**
     * @param
     * @return void
     * @description: load the game file
     * extracted from Main.loadGame()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:13
     * @version: 1.0.0
     **/

    public void loadGame() {
        try {
            loadGameFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * load the game state specification file according to the user selection from file chooser
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/28 17:05
     * @version: 1.0.0
     **/

    public void loadSavedGame() throws IOException, ClassNotFoundException {
        //TODO: this is same with loadGame. Need to improve!
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Saved game file", "*.skbSaved"));
        File saveFile = fileChooser.showOpenDialog(primaryStage);
        FileInputStream fileIn = new FileInputStream(saveFile);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        gameEngine = (GameEngine) in.readObject();
        in.close();
        reloadGrid();
    }


    /**
     * @param
     * @return void
     * @description: close the game, i.e. exist from the whole program
     * extracted from Main.closeGame()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:14
     * @version: 1.0.0
     **/

    public void closeGame() {
        System.exit(0);
    }

    //TODO: this feature is not implemented temporarily replaced by existing from the game

    /**
     * @param
     * @return void
     * @description: undo the previous movement
     * extracted from Main.undo()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:17
     * @version: 1.0.0
     **/

    //TODO: this feature is not implemented yet
    public void undo() {
        gameEngine.undo();
        reloadGrid();
    }

    //TODO: toggle music according to the ratio clicking

    /**
     * @param
     * @return void
     * @description: play or end music
     * extracted from Main.toggleMusic()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:18
     * @version: 1.0.0
     **/

    public void toggleMusic() {
        try {
            musicPlayer.togglePlay();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            if (GameEngine.isDebugActive()) {
                System.out.println(e);
            }
        }
    }

    /**
     * @param
     * @return void
     * @description: enter or exit the debug mode
     * extracted from the Main.toggleDebug()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:21
     * @version: 1.0.0
     **/

    public void toggleDebug() {
        gameEngine.toggleDebug();
        reloadGrid();
    }

    /**
     * @param
     * @return void
     * @description: reset the current level to the original level view
     * extracted from Main.resetLevel()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 20:40
     * @version: 1.0.0
     **/

    //TODO: reset this level to the initial scene
    public void resetLevel() {
        gameEngine.resetCurrentLevel();
        reloadGrid();
    }


    /**
     * @param
     * @return void
     * @description: show the program information by new dialog
     * extracted from the Main.showAbout()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 16:04
     * @version: 1.0.0
     **/

    public void showAbout() {
        String title = "About this game";
        String message = "Game created by Yizirui FANG 20127901\n";

        DialogWindow aboutWindow = new DialogWindow(primaryStage, title, message, null);
        aboutWindow.show();
    }


    /**
     * @param primaryStage
     *         the primary stage of this game page scene
     * @return void
     * @description: load the default file of the map for the game
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 21:08
     * @version:
     **/


    private void loadDefaultSaveFile(Stage primaryStage) {
        GamePageController.primaryStage = primaryStage;
        InputStream defaultInputStream = GamePageController.class.getClassLoader().getResourceAsStream("level/SampleGame.skb");
        initializeGame(defaultInputStream);
        setEventFilter();
    }

    //TODO: investigate for these variable, SaveFile, gameEngineer, primarilyStage should be in parameters or in the field

    /**
     * @param
     * @return void
     * @throw FileNotFoundException
     * @description: load the user defined map file
     * extracted from Main.loadGameFile()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:14
     * @version: 1.0.0
     **/


    private void loadGameFile() throws FileNotFoundException {
        OperateFile operateFile = new OperateFile();
        File saveFile = operateFile.selectGameFile(primaryStage);

        //TODO: refactor the if statement
        if (saveFile != null) {
            initializeGame(new FileInputStream(saveFile));
        }
    }


    /**
     * @param inputGameFile
     *         The input file of the map of this game
     * @return void
     * @description: initialize the grid and the singleton, game engine for this game.
     * extracted from Main.initializeGame()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 17:06
     * @version: 1.0.0
     **/

    private void initializeGame(InputStream inputGameFile) {
        gameEngine = new GameEngine(inputGameFile, true);
        reloadGrid();
    }


    /**
     * @param
     * @return void
     * @description: reload the GridPane grid of the current game level
     * extracted from Main.reloadGrid()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 17:08
     * @version: 1.0.0
     **/

    private void reloadGrid() {
        //TODO: refactor the if statement
        if (gameEngine.isGameComplete()) {
            showVictoryMessage();
            return;
        }

        Level currentLevel = gameEngine.getCurrentLevel();
        Level.LevelIterator levelIterator = currentLevel.getIterator();
        gameGrid.getChildren().clear();
        while (levelIterator.hasNext()) {
            addObjectToGrid(levelIterator.next(), levelIterator.getCurrentPosition());
        }
        gameGrid.autosize();
        primaryStage.sizeToScene();
    }

    /**
     * @param gameObject
     *         The category of game object
     * @param location
     *         The location of this new game object
     * @return void
     * @description: add the game object into the grid of the current level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 19:10
     * @version: 1.0.0
     **/


    private void addObjectToGrid(GameObject gameObject, Point location) {
        GraphicObjectFactory graphicObjectFactory = new GraphicObjectFactory();
        Rectangle graphicObject = graphicObjectFactory.getGraphicObject(gameObject);
        gameGrid.add(graphicObject, location.y, location.x);
    }

    /**
     * @param
     * @return void
     * @description: show the victory message when all levels of the game by a new dialog window
     * extracted from Main.showVictoryMessage()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 20:48
     * @version: 1.0.0
     **/


    private static void showVictoryMessage() {
        String dialogTitle = "Game Over!";
        String dialogMessage = "You completed " + gameEngine.mapSetName + " in " + gameEngine.movesCount + " moves!";
        MotionBlur motionBlur = new MotionBlur(2, 3);

        DialogWindow messageWindow = new DialogWindow(primaryStage, dialogTitle, dialogMessage, motionBlur);
        messageWindow.show();
    }

    /**
     * @param
     * @return void
     * @description: the event filter to the keyboard event at the primary stage window of the game page
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 13:10 given
     * @version: 1.0.0
     **/


    private void setEventFilter() {
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            gameEngine.handleKey(event.getCode());
            reloadGrid();
        });
    }
}

