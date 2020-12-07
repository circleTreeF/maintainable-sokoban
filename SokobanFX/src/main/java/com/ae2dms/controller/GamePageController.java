package com.ae2dms.controller;


import com.ae2dms.Main;
import com.ae2dms.model.GameEngine;
import com.ae2dms.model.GameObject;
import com.ae2dms.model.GraphicObjectFactory;
import com.ae2dms.model.Level;
import com.ae2dms.view.DialogWindow;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.ae2dms.Main.gameEngine;
import static com.ae2dms.Main.primaryStage;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 * <p>
 * This class includes controller for the {@code GamePage.fxml}, which is the page of playing this game
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:57
 */
public class GamePageController {
    private final Number PAGE_WIDTH = 1000;
    private final Number PAGE_HEIGHT = 750;
    private final double GRID_LENGTH = 30.0d;
    @FXML
    public Text bombCount;
    public MenuItem loadMusicButton;
    public MenuItem restartButton;
    public RadioMenuItem toggleMusicMenuItem;
    @FXML
    private GridPane gameGrid;
    @FXML
    Text currentMovesCount;
    @FXML
    Text previousMoves;
    MusicPlayer musicPlayer;
    private EventHandler<KeyEvent> movesFilter;
    private EventHandler<MouseEvent> bombHandler;

    /**
     * initialize the game page element, gameGrid. Display the initial level of the map to the game page
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 13:23
     * @version: 1.0.0
     **/


    public void initialize() {
        String defaultMusic = "puzzle_theme.wav";
        setEventFilter();
        setBombCountListen();
        setMovesCountEventListener();
        initializeGameStateBrief();
        gameGridClickEventListener();
        musicPlayer = new MusicPlayer();
        reloadGrid();
    }


    /**
     * save the current game level to file
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * extracted from Main.saveGame()
     * @date: 2020/11/15 15:11
     * @version: 1.0.0
     **/

    public void saveGame() throws IOException {
        FileOperator fileOperator = new FileOperator();
        File savedLocation = fileOperator.selectSaveGamePath(Main.primaryStage);
        gameEngine.saveGame(savedLocation);
    }


    /**
     * close the game, i.e. exist from the whole program
     * extracted from Main.closeGame()
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:14
     * @version: 1.0.0
     **/

    public void closeGame() {
        System.exit(0);
    }


    /**
     * undo the previous movement
     * extracted from Main.undo()
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:17
     * @version: 1.0.0
     **/

    public void undo() {
        gameEngine.undo();
        reloadGrid();
    }

    /**
     * play or end music
     * extracted from Main.toggleMusic()
     *
     * @return void
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
     * enter or exit the debug mode
     * extracted from the Main.toggleDebug()
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:21
     * @version: 1.0.0
     **/

    public void toggleDebug() {
        gameEngine.toggleDebug();
        reloadGrid();
    }

    /**
     * reset the current level to the original level view
     * extracted from Main.resetLevel()
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 20:40
     * @version: 1.0.0
     **/

    public void resetLevel() {
        gameEngine.resetCurrentLevel();
        reloadGrid();
    }


    /**
     * show the program information by new dialog
     * extracted from the Main.showAbout()
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 16:04
     * @version: 1.0.0
     **/

    public void showAbout() {
        String title = "About this game";
        String message = "Game created by Yizirui FANG 20127901\n";

        DialogWindow aboutWindow = new DialogWindow(Main.primaryStage, title, message, null);
        aboutWindow.show();
    }


    /**
     * Back to the main starting page from the game page
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/2 15:33
     * @version: 1.0.0
     **/


    public void backToMain() {
        BackToMain.back();
        musicPlayer.stop();
        removeEventFilter();
    }



    /**
     * reload the GridPane grid of the current game level
     *
     * @return void
     * extracted from Main.reloadGrid()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 17:08
     * @version: 1.0.0
     **/

    private void reloadGrid() {
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
        Main.primaryStage.sizeToScene();
    }

    /**
     * add the game object into the grid of the current level
     *
     * @param gameObject
     *         The category of game object
     * @param location
     *         The location of this new game object
     * @return void
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
     * show the victory message when all levels of the game by a new dialog window
     *
     * @return void
     * extracted from Main.showVictoryMessage()
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 20:48
     * @version: 1.0.0
     **/


    private void showVictoryMessage() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MarkLogWindow.fxml"));
        Parent page;
        try {
            page = loader.load();
            Scene gameScene = new Scene(page, 400, 300);
            Stage victoryStage = new Stage();
            victoryStage.initModality(Modality.APPLICATION_MODAL);
            victoryStage.initOwner(primaryStage);
            victoryStage.sizeToScene();
            victoryStage.setResizable(false);
            victoryStage.setTitle("Game Over!");
            victoryStage.initStyle(StageStyle.UNDECORATED);
            victoryStage.setScene(gameScene);
            victoryStage.show();
            musicPlayer.stop();
            removeEventFilter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * the event filter to the keyboard event at the primary stage window of the game page
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 13:10 given
     * @version: 1.0.0
     **/


    private void setEventFilter() {
        movesFilter = event -> {
            gameEngine.handleKey(event.getCode());
            reloadGrid();
        };
        primaryStage.getScene().getWindow().addEventFilter(KeyEvent.KEY_PRESSED, movesFilter);
    }


    /**
     * remove the event filter set on this scene, before changing the scene
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 1:54
     * @version: 1.0.0
     **/


    private void removeEventFilter() {
        primaryStage.getScene().getWindow().removeEventFilter(KeyEvent.KEY_PRESSED, movesFilter);
        gameGrid.removeEventFilter(MouseEvent.MOUSE_CLICKED, bombHandler);
    }


    /**
     * set a change listener to be notified automatically when the move count has changed
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/30 22:29
     * @version: 1.0.0
     **/


    private void setMovesCountEventListener() {
        gameEngine.currentLevelMovesCountsProperty.addListener(
                (observableValue, oldValue, newValue) -> currentMovesCount.setText(newValue.toString())
        );
        gameEngine.previousLevelsMovesCountsProperty.addListener(
                (observableValue, oldValue, newValue) -> previousMoves.setText(newValue.toString())
        );
    }


    /**
     * set the initial value for the information in the game state brief component
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/30 23:38
     * @version: 1.0.0
     **/


    private void initializeGameStateBrief() {
        currentMovesCount.setText(String.valueOf(gameEngine.currentLevelMovesCountsProperty.get()));
        previousMoves.setText(String.valueOf(gameEngine.previousLevelsMovesCountsProperty.get()));

    }


    /**
     * This method is to bind the text element for presenting the number of available bombs in the game information brief
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/5 2:42
     * @version: 1.0.0
     **/


    private void setBombCountListen() {
        bombCount.textProperty().bind(gameEngine.bombCountProperty.asString());
    }


    /**
     * This method is to set the event listener on the mouse clicking event for the view element {@code gameGrid}
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/5 2:44
     * @version: 1.0.0
     **/


    private void gameGridClickEventListener() {
        bombHandler = mouseEvent -> {
            System.out.println("X " + mouseEvent.getX() + " Y " + mouseEvent.getY());
            int column = (int) Math.floor(mouseEvent.getX() / GRID_LENGTH);
            int row = (int) Math.floor(mouseEvent.getY() / GRID_LENGTH);
            transportKeeper(column, row);
            destroyWall(column, row);
        };
        gameGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, bombHandler);
    }


    /**
     * destroy the wall specified by the horizontal position {@code column} and the vertical position {@code row}
     *
     * @param column
     *         the index of column of {@code gameGrid}
     * @param row
     *         the index of row of {@code gameGrid}
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/5 2:45
     * @version: 1.0.0
     **/


    private void destroyWall(int column, int row) {
        Level currentLevel = gameEngine.getCurrentLevel();
        if (currentLevel.objectsGrid.getGameObjectAt(row, column) == GameObject.WALL) {
            gameEngine.wallBomb(column, row);
            reloadGrid();
        }
    }

    /**
     * transport the new location of the keeper by the new horizontal position {@code column} and vertical position {@code row}
     *
     * @param column
     *         the index of column of {@code gameGrid}
     * @param row
     *         the index of row of {@code gameGrid}
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/5 4:19
     * @version: 1.0.0
     **/


    private void transportKeeper(int column, int row) {
        Level currentLevel = gameEngine.getCurrentLevel();
        if (currentLevel.objectsGrid.getGameObjectAt(row, column) == GameObject.FLOOR) {
            gameEngine.keeperTransport(column, row);
            reloadGrid();
        }
    }


    /**
     * This method is the action on the button clicked for {@code Load Music} to load the user-specified file in the file chooser
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/5 2:47
     * @version: 1.0.0
     **/


    public void onLoadMusicButtonClicked() {
        FileOperator fileOperator = new FileOperator();
        File musicFile = fileOperator.selectMusic(Main.primaryStage);
        if (musicFile == null) {
            System.out.println("Select nothing!");
            throw new NullPointerException();
        }
        musicPlayer.stop();
        musicPlayer = new MusicPlayer(musicFile);
    }


    /**
     * reload and rebase the current music broadcasting clip to the beginning of the music file. And select the radio item <b>Toggle Music</b>
     * <p>This method will be called when the menu item <b>Restart Music</b> is clicked</p>
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/5 21:39
     * @version: 1.0.0
     **/

    public void onRestartButtonClicked() {
        try {
            musicPlayer.restart();
            toggleMusicMenuItem.setSelected(true);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}

