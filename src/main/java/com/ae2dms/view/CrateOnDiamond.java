package com.ae2dms.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: the graphic view of game object CRATE_ON_DIAMOND, crate which is in the diamond location.
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 20:49
 */


public class CrateOnDiamond extends Rectangle {
    /**
     * constructor
     *
     * @param
     * @description: construct the view of crate which is in the diamond location in the game view
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 21:50
     * @version: 1.0.0
     **/

    public CrateOnDiamond() {
        initializeCommonCrateOnDiamond();
    }

    /**
     * constructor
     *
     * @param isDebug
     *         true if the game is in debug mode; false otherwise
     * @description: construct the view of crate which is in the diamond location in the game view in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 21:52
     * @version: 1.0.0
     **/

    public CrateOnDiamond(Boolean isDebug) {
        initializeCommonCrateOnDiamond();
        this.setStroke(Color.RED);
        this.setStrokeWidth(0.25);
    }

    /**
     * @param
     * @return void
     * @description: initialize the wall with common features between normal game object CRATE_ON_DIAMOND and it in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 21:53
     * @version: 1.0.0
     **/


    public void initializeCommonCrateOnDiamond() {
        this.setFill(Color.DARKCYAN);
        this.setHeight(30);
        this.setWidth(30);
        this.setArcHeight(50);
        this.setArcWidth(50);
    }
}
