package com.ae2dms.model;

import com.ae2dms.GameObject;
import com.ae2dms.controller.GamePageController;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;

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
        GameEngine gameEngine = new GameEngine(inputStream);
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
        GameEngine gameEngine = new GameEngine(inputStream);
        gameEngine.handleKey(KeyCode.LEFT);
        gameEngine.undo();
        int actualMoveCount = gameEngine.currentLevelMovesCountsProperty.get();
        assertEquals(1, actualMoveCount);

        InputStream sameInputStream = GamePageController.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine sameGameEngine = new GameEngine(sameInputStream);
        assertEquals(sameGameEngine.getCurrentLevel().objectsGrid.toString(), gameEngine.getCurrentLevel().objectsGrid.toString());
        assertEquals(sameGameEngine.getCurrentLevel().diamondsGrid.toString(), gameEngine.getCurrentLevel().diamondsGrid.toString());
    }

    @Test
    void resetCurrentLevel() {
        InputStream inputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream);
        gameEngine.handleKey(KeyCode.LEFT);
        gameEngine.handleKey(KeyCode.UP);
        gameEngine.handleKey(KeyCode.LEFT);
        gameEngine.resetCurrentLevel();

        InputStream sameInputStream = GamePageController.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine sameGameEngine = new GameEngine(sameInputStream);
        int actualMoveCount = gameEngine.currentLevelMovesCountsProperty.get();
        assertEquals(0, actualMoveCount);
        assertEquals(sameGameEngine.getCurrentLevel().objectsGrid.toString(), gameEngine.getCurrentLevel().objectsGrid.toString());
        assertEquals(sameGameEngine.getCurrentLevel().diamondsGrid.toString(), gameEngine.getCurrentLevel().diamondsGrid.toString());

    }

    @Test
    void saveGame() throws IOException, ClassNotFoundException {
        String testFileName = "gameEngine.json";
        File file = new File(testFileName);
        InputStream defaultInputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/DebugLevel.skb");
        GameEngine gameEngine = new GameEngine(defaultInputStream);
        gameEngine.saveGame(file);
    }

    @Test
    void getMovesCount() {
    }

    @Test
    void setMovesCount() {
    }

    @Test
    void attach() {
    }

    @Test
    void notifyAllObservers() {

    }

    @Test
    void movesCountTest() {
        InputStream inputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream);
        gameEngine.handleKey(KeyCode.LEFT);
        gameEngine.handleKey(KeyCode.UP);
        gameEngine.handleKey(KeyCode.RIGHT);
        gameEngine.handleKey(KeyCode.DOWN);
        assertEquals(4, gameEngine.currentLevelMovesCountsProperty.get());
    }

    @Test
    void testMove() {
    }

    @Test
    void testIsGameComplete() {
        InputStream inputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream);
        assertFalse(gameEngine.isGameComplete());
    }

    @Test
    void wallBomb() {
        InputStream inputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream);
        gameEngine.wallBomb(10,2);
        assertEquals(GameObject.FLOOR, gameEngine.getCurrentLevel().objectsGrid.getGameObjectAt(2,10));
    }

    @Test
    void keeperTransport() {
        InputStream inputStream = GameEngineTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream);
        gameEngine.keeperTransport(10,2);
        assertEquals(new Point(2,10), gameEngine.getCurrentLevel().getKeeperPosition());
    }
}