package com.ae2dms.controller;


import com.ae2dms.GameObject;
import com.ae2dms.Main;
import com.ae2dms.model.GameEngine;
import com.ae2dms.model.GraphicObjectFactory;
import com.ae2dms.model.Level;
import com.ae2dms.view.DialogWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static com.ae2dms.Main.gameEngine;
import static com.ae2dms.Main.primaryStage;

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
    private final Number PAGE_WIDTH = 1000;
    private final Number PAGE_HEIGHT = 750;
    @FXML
    private GridPane gameGrid;
    @FXML
    Text currentMovesCount;
    @FXML
    Text previousMoves;
    MusicPlayer musicPlayer;
    private EventHandler movesFilter;

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
        setEventFilter();
        setMovesCountEventListener();
        initializeGameStateBrief();
        musicPlayer = new MusicPlayer(defaultMusic);
        centerGameGrid();
        reloadGrid();
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
        FileOperator fileOperator = new FileOperator();
        File savedLocation = fileOperator.selectSaveGamePath(Main.primaryStage);
        gameEngine.saveGame(savedLocation);
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

        DialogWindow aboutWindow = new DialogWindow(Main.primaryStage, title, message, null);
        aboutWindow.show();
    }


    /**
     * Back to the main starting page from the game page
     *
     * @param actionEvent
     *         the mouse event of the user
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/2 15:33
     * @version:
     **/


    public void backToMain(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            musicPlayer.stop();
//            primaryStage.removeEventFilter(KeyEvent.KEY_PRESSED, event -> {
//                gameEngine.handleKey(event.getCode());
//                reloadGrid();
//            });
            //primaryStage.getScene().
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //TODO: investigate for these variable, SaveFile, gameEngineer, primarilyStage should be in parameters or in the field


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
        //gameGrid.setAlignment(Pos.CENTER);
        //gameGrid.setLayoutX((1000-gameGrid.getWidth())/2);
        //gameGrid.setLayoutY((750-gameGrid.getHeight())/2);
        //gameGrid.autosize();
        //centerGameGrid();
        Main.primaryStage.sizeToScene();
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


    private void showVictoryMessage() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MarkLogWindow.fxml"));
        Parent page = null;
        try {
            page = loader.load();
            Scene gameScene = new Scene(page, 400, 300);
            Stage victoryStage = new Stage();
            victoryStage.initModality(Modality.APPLICATION_MODAL);
            victoryStage.initOwner(primaryStage);
            victoryStage.sizeToScene();
            //victoryStage.setResizable(false);
            victoryStage.setTitle("Game Over!");
            victoryStage.setScene(gameScene);
            victoryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        String dialogTitle = "Game Over!";
//        String dialogMessage = "You completed " + gameEngine.mapSetName + " in " + gameEngine.previousLevelsMovesCountsProperty.get() + " moves!";
//        MotionBlur motionBlur = new MotionBlur(2, 3);
//
//        DialogWindow messageWindow = new DialogWindow(Main.primaryStage, dialogTitle, dialogMessage, motionBlur);
//        messageWindow.show();
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
        //Scene testScene = primaryStage.getScene();
        primaryStage.getScene().getWindow().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            gameEngine.handleKey(event.getCode());
            reloadGrid();
        });
    }


//    private EventHandler movesEvent(KeyEvent event) {
//        gameEngine.handleKey(event.getCode());
//        reloadGrid();
//    }

    /**
     * set a change listener to be notified automatically when the move count has changed
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/30 22:29
     * @version: 1.0.0
     **/


    private void setMovesCountEventListener() {
        gameEngine.currentLevelMovesCountsProperty.addListener(
                new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                        currentMovesCount.setText(newValue.toString());
                    }
                }
        );
        gameEngine.previousLevelsMovesCountsProperty.addListener(
                new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                        previousMoves.setText(newValue.toString());
                    }
                }
        );
    }


    /**
     * set the initial value for the information in the game state brief component
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/30 23:38
     * @version: 1.0.0
     **/


    private void initializeGameStateBrief() {
        currentMovesCount.setText(String.valueOf(gameEngine.currentLevelMovesCountsProperty.get()));
        previousMoves.setText(String.valueOf(gameEngine.previousLevelsMovesCountsProperty.get()));
    }


    private void centerGameGrid() {
        gameGrid.widthProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldWidth, Number newWidth) {
                       gameGrid.layoutXProperty().setValue(BigDecimal.valueOf(PAGE_WIDTH.floatValue()/2).subtract(BigDecimal.valueOf(newWidth.floatValue()/2)));
                    }
                }
        );

        gameGrid.heightProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldHeight, Number newHeight) {
                        gameGrid.layoutYProperty().setValue(BigDecimal.valueOf(PAGE_HEIGHT.floatValue()/2).subtract(BigDecimal.valueOf(newHeight.floatValue()/2)));
                    }
                }
        );
    }


}

