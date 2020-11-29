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
public class GamePageObserver extends Observer {
    int movesCount;


    public GamePageObserver(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
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
