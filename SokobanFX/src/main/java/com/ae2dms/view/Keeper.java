package com.ae2dms.view;

import javafx.scene.paint.Color;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 * <p>
 * The graphic view of game object keeper
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 20:42
 */


public class Keeper extends ObjectView {

    /**
     * construct the normal keeper in the game view
     *
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:46
     * @version:
     **/

    public Keeper() {
        initializeCommonSize();
        initializeCommonKeeper();
    }

    /**
     * construct the keeper in the game view in debug mode
     *
     * @param isDebug
     *         true if the game is in debug mode; false otherwise
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:45
     * @version:
     **/


    public Keeper(Boolean isDebug) {
        initializeCommonSize();
        initializeCommonKeeper();
        this.setStroke(Color.RED);
        this.setStrokeWidth(0.25);
    }

    /**
     * initialize the wall with common features between normal keeper and keeper in debug mode
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:28
     * @version: 1.0.0
     **/

    public void initializeCommonKeeper() {
        this.setFill(Color.RED);
        this.setArcHeight(50);
        this.setArcWidth(50);
    }
}
