package com.ae2dms.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: the graphic view of game object floor
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 20:47
 */


public class Floor extends Rectangle {

    /**
     * constructor
     *
     * @param
     * @description: construct the normal floor in the game view
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:50
     * @version: 1.0.0
     **/


    public Floor() {
        initializeCommonFloor();
    }

    /**
     * constructor
     *
     * @param isDebug
     *         true if the game is in debug mode; false otherwise
     * @description: construct the floor in the game view in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:50
     * @version: 1.0.0
     **/

    public Floor(Boolean isDebug) {
        initializeCommonFloor();
        this.setStroke(Color.RED);
        this.setStrokeWidth(0.25);
    }

    /**
     * @param
     * @return void
     * @description: initialize the wall with common features between normal floor and floor in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:51
     * @version: 1.0.0
     **/


    public void initializeCommonFloor() {
        this.setFill(Color.WHITE);
        this.setHeight(30);
        this.setWidth(30);
        this.setArcHeight(50);
        this.setArcWidth(50);
    }

}
