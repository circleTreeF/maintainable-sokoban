package com.ae2dms;

import com.ae2dms.model.GameEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public static Stage primaryStage;
    public static GameEngine gameEngine;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage
     *         the primary stage of this game page scene
     * @return void
     * @description: set the to menu of the initial interface of the game
     * In the menu, 3 options are "file", "level", "about"
     * * Under each option, there are menu items.
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:11 given
     * @version: 1.0.0
     **/
    /**
     * @param primaryStage
     *         the primary stage of this game page scene
     * @return void
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
