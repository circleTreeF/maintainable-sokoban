package com.ae2dms.model;

import com.ae2dms.controller.GamePageController;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 *
 * @description: This class is the test class for the class GameEngine in the model package of this project
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/25 14:35
 */
class GameEngineTest {

    @Test
    void isDebugActive() {
        InputStream inputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
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

    @Test
    void undo() {
        InputStream inputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream, true);
        gameEngine.handleKey(KeyCode.LEFT);
        gameEngine.undo();
        assertEquals(1, gameEngine.movesCount);

        InputStream sameInputStream = GamePageController.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine sameGameEngine = new GameEngine(sameInputStream, true);
        assertEquals(sameGameEngine.getCurrentLevel().objectsGrid.toString(), gameEngine.getCurrentLevel().objectsGrid.toString());
        assertEquals(sameGameEngine.getCurrentLevel().diamondsGrid.toString(),gameEngine.getCurrentLevel().diamondsGrid.toString());
    }

    @Test
    void resetCurrentLevel() {
        InputStream inputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream, true);
        gameEngine.handleKey(KeyCode.LEFT);
        gameEngine.handleKey(KeyCode.UP);
        gameEngine.handleKey(KeyCode.LEFT);
        gameEngine.resetCurrentLevel();

        InputStream sameInputStream = GamePageController.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine sameGameEngine = new GameEngine(sameInputStream, true);
        assertEquals(0, gameEngine.movesCount);
        assertEquals(sameGameEngine.getCurrentLevel().objectsGrid.toString(), gameEngine.getCurrentLevel().objectsGrid.toString());
        assertEquals(sameGameEngine.getCurrentLevel().diamondsGrid.toString(),gameEngine.getCurrentLevel().diamondsGrid.toString());

    }
}