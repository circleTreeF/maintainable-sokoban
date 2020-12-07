package com.ae2dms.view;

import javafx.scene.paint.Color;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 * <p>
 * The graphic view of game object wall
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 17:56
 */


public class Wall extends ObjectView {
    /**
     * construct the normal wall in the game view
     *
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:24
     * @version: 1.0.0
     **/

    public Wall() {
        initializeCommonSize();
        initializeCommonWall();
    }

    /**
     * constructor to construct the wall in the game view in debug mode
     *
     * @param isDebug
     *         true if the game is in debug mode; false otherwise
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:25
     * @version: 1.0.0
     **/


    public Wall(Boolean isDebug) {
        initializeCommonSize();
        initializeCommonWall();
        this.setStroke(Color.RED);
        this.setStrokeWidth(0.25);
    }

    /**
     * initialize the wall with common features between normal wall and wall in debug mode
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:24
     * @version: 1.0.0
     **/

    public void initializeCommonWall() {
        this.setFill(Color.BLACK);
    }
}
