package com.ae2dms.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: the graphic view of game object wall
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 17:56
 */


public class Wall extends Rectangle {
    /**
     * constructor
     *
     * @param
     * @description: construct the normal wall in the game view
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:24
     * @version: 1.0.0
     **/

    public Wall() {
        initializeCommonWall();
        //this = FXMLLoader.load(getClass().getResource("/view/Wall.fxml"));
    }

    /**
     * constructor
     *
     * @param isDebug
     *         true if the game is in debug mode; false otherwise
     * @description: construct the wall in the game view in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:25
     * @version: 1.0.0
     **/


    public Wall(Boolean isDebug) {
        // FXMLLoader.load(getClass().getResource("/view/Wall.fxml"));
        initializeCommonWall();
        this.setStroke(Color.RED);
        this.setStrokeWidth(0.25);
    }

    /**
     * @param
     * @return void
     * @description: initialize the wall with common features between normal wall and wall in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:24
     * @version: 1.0.0
     **/

    public void initializeCommonWall() {
        this.setFill(Color.BLACK);
        this.setHeight(30);
        this.setWidth(30);
    }
}
