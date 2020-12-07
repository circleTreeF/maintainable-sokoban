package com.ae2dms.view;

import javafx.scene.paint.Color;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 * <p>
 * The graphic view of game object CRATE_ON_DIAMOND, crate which is in the diamond location.
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 20:49
 */


public class CrateOnDiamond extends ObjectView {
    /**
     * initialize the wall with common features between normal crate and crate in debug mode
     *
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 21:50
     * @version: 1.0.0
     **/

    public CrateOnDiamond() {
        initializeCommonSize();
        initializeCommonCrateOnDiamond();
    }

    /**
     * construct the view of crate which is in the diamond location in the game view in debug mode
     *
     * @param isDebug
     *         true if the game is in debug mode; false otherwise
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 21:52
     * @version: 1.0.0
     **/

    public CrateOnDiamond(Boolean isDebug) {
        initializeCommonSize();
        initializeCommonCrateOnDiamond();
        this.setStroke(Color.RED);
        this.setStrokeWidth(0.25);
    }

    /**
     * initialize the wall with common features between normal game object CRATE_ON_DIAMOND and it in debug mode
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 21:53
     * @version: 1.0.0
     **/


    public void initializeCommonCrateOnDiamond() {
        this.setFill(Color.DARKCYAN);
        this.setArcHeight(50);
        this.setArcWidth(50);
    }
}
