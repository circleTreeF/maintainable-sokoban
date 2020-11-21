package com.ae2dms.view;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: the graphic view of game object diamond
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 20:24
 */


public class Diamond extends Rectangle {
    /**
     * constructor
     *
     * @param
     * @description: construct the normal diamond in the game view
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:31
     * @version: 1.0.0
     **/

    public Diamond() {
        initializeCommonDiamond();
    }

    /**
     * constructor
     *
     * @param isDebug
     *         true if the game is in debug mode; false otherwise
     * @description: construct the diamond in the game view in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:32
     * @version: 1.0.0
     **/


    public Diamond(Boolean isDebug) {
        initializeCommonDiamond();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), this);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.2);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    /**
     * @param
     * @return void
     * @description: initialize the wall with common features between normal diamond and diamond in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:34
     * @version: 1.0.0
     **/

    public void initializeCommonDiamond() {
        this.setFill(Color.DEEPSKYBLUE);
        this.setHeight(30);
        this.setWidth(30);
        this.setArcHeight(50);
        this.setArcWidth(50);
    }
}
