package com.ae2dms.controller;

import com.ae2dms.Main;
import com.ae2dms.model.GameEngine;
import com.ae2dms.view.DialogWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

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
    public ImageView highMarkBoardButton;
    public ImageView loadNewGameButton;
    public ImageView loadSavedGameButton;
    public ImageView exitButton;
    public ImageView infoButton;


    /**
     * @return void
     * @description start game when the start button is clicked
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 22:14
     * @version: 1.0.0
     **/


    public void onStartClicked() {
        loadDefaultSaveFile();
        loadGamePage();

    }


    /**
     * Start the new game selected by the user from the file chooser
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/1 15:14
     * @version: 1.0.0
     **/


    public void onLoadNewGameClicked() {
        loadGame();
        loadGamePage();
    }

    /**
     * Resume the saved game selected by the user from the file chooser
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/1 15:15
     * @version: 1.0.0
     **/


    public void onLoadSavedGameClick() {
        loadSavedGame();
        loadGamePage();
    }

    /**
     * Exit the game
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/1 15:16
     * @version: 1.0.0
     **/


    public void onExitClicked() {
        System.exit(0);
    }


    /**
     * @return void
     * @description new dialog for about information
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/9 22:15
     * @version: 1.0.0
     **/


    public void onAboutClicked() {
        String title = "About this game";
        String message = "Game created by Yizirui FANG 20127901\n";

        DialogWindow aboutWindow = new DialogWindow(primaryStage, title, message, null);
        aboutWindow.show();
    }

    /**
     * load the highest mark board view page after the button, "High Mark Board", is clicked.
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 0:53
     * @version: 1.0.0
     **/


    public void onHighMarkBoardClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MarkBoard.fxml"));
            Parent page = loader.load();
            primaryStage.setScene(new Scene(page, 1000, 750));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * load the game playing page with the user specified map
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 0:53
     * @version: 1.0.0
     **/


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
        gameEngine = new GameEngine(inputGameFile);
    }


    /**
     * try to load the game file
     * extracted from Main.loadGame()
     *
     * @return void
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
     * load the user defined map file in the file chooser
     * extracted from Main.loadGameFile()
     *
     * @return void
     * @throws FileNotFoundException
     *         Signals that an attempt to open the file denoted by a specified pathname has failed.
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/15 15:14
     * @version: 1.0.0
     */


    private void loadGameFile() throws FileNotFoundException {
        FileOperator fileOperator = new FileOperator();
        File saveFile = fileOperator.selectGameFile(Main.primaryStage);

        //TODO: refactor the if statement
        if (saveFile != null) {
            initializeGame(new FileInputStream(saveFile));
        }
    }

    /**
     * load the saved game map file specified by the user in the file chooser, in format {@code .skbSaved}
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 0:58
     * @version: 1.0.0
     **/


    private void loadSavedGame() {
        FileOperator fileOperator = new FileOperator();
        File saveFile = fileOperator.selectSavedGame(Main.primaryStage);
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream(saveFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            gameEngine = (GameEngine) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is to provide the new stage/window based on the current stage/window. This new window will provide the user the web browser with the homepage set as the game information page.
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 1:29
     * @version: 1.0.0
     **/


    public void onInfoButtonClicked() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/InfoPage.fxml"));
        FlowPane webBrowser;
        try {
            webBrowser = loader.load();
            Stage infoStage = new Stage();
            infoStage.setScene(new Scene(webBrowser, 1366, 768));
            infoStage.setResizable(false);
            infoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
