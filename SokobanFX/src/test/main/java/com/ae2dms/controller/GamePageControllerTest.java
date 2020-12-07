package com.ae2dms.controller;

import com.ae2dms.model.GameEngine;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @description: This class is the test class for the class GamePageController in the model package of this project
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/25 12:22
 */
class GamePageControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initialize() {
    }

    @Test
    void saveGame() {
    }

    @Test
    void loadGame() {
    }

    @Test
    void closeGame() {
    }

    @Test
    void undo() {
    }

    @Test
    void toggleMusic() {
    }

    @Test
    void toggleDebug() {
    }

    @Test
    void resetLevel() {
    }

    @Test
    void showAbout() {
    }

    @Test
    void loadSavedGame() {
        GamePageController gamePageController = new GamePageController();
        gamePageController.initialize();

    }

    @Test
    void setMovesCountEventListener() {
        InputStream inputStream = GamePageControllerTest.class.getClassLoader().getResourceAsStream("level/debugGame.skb");
        GameEngine gameEngine = new GameEngine(inputStream);
        gameEngine.handleKey(KeyCode.LEFT);
        gameEngine.handleKey(KeyCode.UP);
        gameEngine.handleKey(KeyCode.RIGHT);
        gameEngine.handleKey(KeyCode.DOWN);
        assertEquals(4, gameEngine.currentLevelMovesCountsProperty.get());
    }
}