package com.ae2dms.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import static com.ae2dms.Main.primaryStage;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @description: This class is to set the scene of the {@code primaryStage} to the main dashboard scene.
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/4 2:28
 */
public class BackToMain {

    /**
     * This method is a public static method. This method can be called at the whole project to set the {@code scene} of {@code primaryStage} to be the dashboard scene, {@code MainPage.fxml}
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 3:22
     * @version:
     **/


    public static void back() {
        try {
            Parent root = FXMLLoader.load(BackToMain.class.getResource("/view/MainPage.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
