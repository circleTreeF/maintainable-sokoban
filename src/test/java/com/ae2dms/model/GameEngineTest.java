package com.ae2dms.model;

import com.ae2dms.controller.GamePageController;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/25 14:35
 */
class GameEngineTest {

    @Test
    void isDebugActive() {
        InputStream inputStream = GamePageController.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream, true);
        assertFalse(GameEngine.isDebugActive());
    }

    @Test
    void handleKey() {

    }

    @Test
    void move() {
    }

    @Test
    void isGameComplete() {
    }

    @Test
    void getNextLevel() {
    }

    @Test
    void getCurrentLevel() {
    }

    @Test
    void toggleDebug() {
    }
}