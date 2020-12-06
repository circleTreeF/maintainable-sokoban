package com.ae2dms;

import com.ae2dms.model.GameEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class of the whole project. The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/9 21:10 given
 * @version: 1.0
 */
public class Main extends Application {

    /**
     * The stage this game is to present at
     */
    public static Stage primaryStage;
    /**
     * The instance of the current game
     */
    public static GameEngine gameEngine;

    /**
     * The main method to start this project
     *
     * @param args
     *         any input arguments of this project
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 16:55
     * @version: 1.0.0
     **/

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage
     *         the primary stage of this game page scene
     * @return void
     * @throws Exception
     *         This indicates the general exception in this project
     * @description: show the game page of this game by load the view from fxml file in view package
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 21:47
     * @version: 2.0.0
     **/


    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle(GameEngine.GAME_NAME);
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));
        Main.primaryStage.setScene(new Scene(root, 1000, 750));
        Main.primaryStage.show();
        Main.primaryStage.setResizable(false);
    }

}
