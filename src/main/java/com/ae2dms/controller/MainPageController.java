package com.ae2dms.controller;

import com.ae2dms.Main;
import com.ae2dms.model.GameEngine;
import com.ae2dms.view.DialogWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;

import static com.ae2dms.Main.gameEngine;
import static com.ae2dms.Main.primaryStage;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @description: This is the class to control the main page
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/9 21:10
 */
public class MainPageController {
    public ImageView startButton;
    public ImageView aboutButton;

    /**
     * @param mouseEvent
     *         the mouse event of the user
     * @return void
     * @description start game when the start button is clicked
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 22:14
     **/


    public void onStartClicked(MouseEvent mouseEvent) {
        loadDefaultSaveFile();
        loadGamePage();

    }


    /**
     * Start the new game selected by the user from the file chooser
     *
     * @param mouseEvent
     *         the mouse event of the user
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/1 15:14
     * @version:
     **/


    public void onLoadNewGameClicked(MouseEvent mouseEvent) {
        loadGame();
        loadGamePage();
    }

    /**
     * Resume the saved game selected by the user from the file chooser
     *
     * @param mouseEvent
     *         the mouse event of the user
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/1 15:15
     * @version:
     **/


    public void onLoadSavedGameClick(MouseEvent mouseEvent) {
        loadSavedGame();
        loadGamePage();
    }

    /**
     * Exit the game
     *
     * @param mouseEvent
     *         the mouse event of the user
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/1 15:16
     * @version:
     **/


    public void onExitClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }


    /**
     * @param mouseEvent
     *         the mouse event of the user
     * @return void
     * @description new dialog for about information
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 22:15
     **/


    public void onAboutClicked(MouseEvent mouseEvent) {
        String title = "About this game";
        String message = "Game created by Yizirui FANG 20127901\n";

        DialogWindow aboutWindow = new DialogWindow(primaryStage, title, message, null);
        aboutWindow.show();
    }


    private void loadGamePage() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/GamePage.fxml"));
            Parent page = loader.load();
            primaryStage.setScene(new Scene(page, 1000, 750));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return void
     * @description: load the default file of the map for the game
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 21:08
     * @version:
     **/


    private void loadDefaultSaveFile() {
        InputStream defaultInputStream = GamePageController.class.getClassLoader().getResourceAsStream("level/SampleGame.skb");
        initializeGame(defaultInputStream);
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


    private void loadGame() {
        try {
            loadGameFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        FileOperator fileOperator = new FileOperator();
        File saveFile = fileOperator.selectGameFile(Main.primaryStage);

        //TODO: refactor the if statement
        if (saveFile != null) {
            initializeGame(new FileInputStream(saveFile));
        }
    }


    private void loadSavedGame() {
        FileOperator fileOperator = new FileOperator();
        File saveFile = fileOperator.selectSavedGame(Main.primaryStage);
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream(saveFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            gameEngine = (GameEngine) inputStream.readObject();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
