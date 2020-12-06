package com.ae2dms.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 * <p>
 * This class is to test the class GameGrid
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/6 17:38
 */
class GameGridTest {
    private GameGrid gameGrid;

    @BeforeEach
    void setUp() {
        gameGrid = new GameGrid(20, 20);
        gameGrid.putGameObjectAt(GameObject.FLOOR, 10, 10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void translatePoint() {
        assertEquals(new Point(11, 11), GameGrid.translatePoint(new Point(10, 10), new Point(1, 1)));
    }

    @Test
    void getTargetFromSource() {
        assertEquals(GameObject.FLOOR, gameGrid.getTargetFromSource(new Point(9,9), new Point(1,1)));
    }

    @Test
    void getGameObjectAt() {
        assertEquals(GameObject.FLOOR, gameGrid.getGameObjectAt(new Point(10,10)));
    }

    @Test
    void putGameObjectAt() {
        assertEquals(GameObject.FLOOR, gameGrid.getGameObjectAt(10, 10));
    }

    @Test
    void testPutGameObjectAt() {
        gameGrid.putGameObjectAt(GameObject.FLOOR, new Point(12, 10));
        assertEquals(GameObject.FLOOR, gameGrid.getGameObjectAt(12, 10));
    }
}