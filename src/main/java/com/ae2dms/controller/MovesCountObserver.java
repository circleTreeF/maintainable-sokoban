package com.ae2dms.controller;

import com.ae2dms.model.GameEngine;
import com.ae2dms.model.Observer;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @description: The concrete observer the change of moves count of GameEngine
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/29 17:11
 */

//TODO: could be refactor to use the JavaFX build in class IntegerProperty(wrapper class)
public class MovesCountObserver extends Observer {
    int movesCount;

    /**
     * constructor
     * <p>
     * construct this GamePageObserver
     *
     * @param gameEngine
     *         the subject this observer to attach on
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/30 15:32
     * @version:
     **/

    public MovesCountObserver(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.gameEngine.attach(this);
    }

    /**
     * Update the value in observers
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/29 17:17
     * @version:
     **/
    @Override
    public void update() {
        movesCount = gameEngine.getMovesCount();
    }
}
