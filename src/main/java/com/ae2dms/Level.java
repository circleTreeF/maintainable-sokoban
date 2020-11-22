package com.ae2dms;

import com.ae2dms.model.GameEngine;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @description: This is the class for level in the game
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/10 11:10 given
 */

public final class Level implements Iterable<GameObject> {
    /**
     * the GameGrid of all game objects in this level, while the diamonds would be recorded as floor
     */
    public final GameGrid objectsGrid;
    public final GameGrid diamondsGrid;
    private final String name;
    private final int index;
    private int numberOfDiamonds = 0;
    private Point keeperPosition = new Point(0, 0);

    /**
     * constructor
     *
     * @param levelName
     *         the name of the level, got from the .skb map file
     * @param levelIndex
     *         the index of the level, count from 0 depending on the order of occurrence in the map file
     * @param rawLevel
     *         the list of raw level where game object is specified by strings, got from the .skb map file
     * @description: the default constructor of class Level, set objectsGrid as
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 15:38 given
     * @version: 1.0.0
     **/


    public Level(String levelName, int levelIndex, List<String> rawLevel) {
        //TODO: refactor the if statement
        if (GameEngine.isDebugActive()) {
            System.out.printf("[ADDING LEVEL] LEVEL [%d]: %s\n", levelIndex, levelName);
        }

        name = levelName;
        index = levelIndex;

        int rows = rawLevel.size();
        int columns = rawLevel.get(0).trim().length();

        objectsGrid = new GameGrid(rows, columns);
        diamondsGrid = new GameGrid(rows, columns);

        //traverse through the map to define
        for (int row = 0; row < rawLevel.size(); row++) {
            for (int col = 0; col < rawLevel.get(row).length(); col++) {
                GameObject curTile = GameObject.fromChar(rawLevel.get(row).charAt(col));

                if (curTile == GameObject.DIAMOND) {
                    numberOfDiamonds++;
                    diamondsGrid.putGameObjectAt(curTile, row, col);
                    curTile = GameObject.FLOOR;
                } else if (curTile == GameObject.KEEPER) {
                    keeperPosition = new Point(row, col);
                }

                objectsGrid.putGameObjectAt(curTile, row, col);
                curTile = null;
            }
        }
    }

    /**
     * @param
     * @return boolean
     * @description: check if the current level of the game is completed by comparing the number of crate and the number of the crates already in the diamond position
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/12 16:00 given
     * @version: 1.0.0
     **/


    public boolean isComplete() {
        int cratedDiamondsCount = 0;
        for (int row = 0; row < objectsGrid.ROWS; row++) {
            for (int col = 0; col < objectsGrid.COLUMNS; col++) {
                if (objectsGrid.getGameObjectAt(col, row) == GameObject.CRATE && diamondsGrid.getGameObjectAt(col, row) == GameObject.DIAMOND) {
                    cratedDiamondsCount++;
                }
            }
        }
        return cratedDiamondsCount >= numberOfDiamonds;
    }

    /**
     * @param
     * @return java.lang.String
     * @description: get the name of the level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 22:17 given
     * @version: 1.0.0
     **/


    public String getName() {
        return name;
    }

    /**
     * @param
     * @return int
     * @description: get the index of the level in the list of levels
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 22:17 given
     * @version: 1.0.0
     **/


    public int getIndex() {
        return index;
    }

    /**
     * @param
     * @return java.awt.Point
     * @description: get the current position of keep in the current level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 17:28 given
     * @version: 1.0.0
     **/

    public Point getKeeperPosition() {
        return keeperPosition;
    }

    /**
     * @param source
     *         the source point
     * @param delta
     *         the offset for the source point to find the transferred point
     * @return com.ae2dms.GameObject
     * @description: get the game object transferred from source by delta
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 23:35 given
     * @version:
     **/


    public GameObject getTargetObject(Point source, Point delta) {
        return objectsGrid.getTargetFromSource(source, delta);
    }

    /**
     * @param
     * @return java.lang.String
     * @description: //TODO
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 22:30 given
     * @version:
     **/

    @Override
    public String toString() {
        return objectsGrid.toString();
    }

    /**
     * @param
     * @return java.util.Iterator<com.ae2dms.GameObject>
     * @description: return a new inner class of this class, Level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 23:07 given
     * @version: 1.0.0
     **/


    @Override
    public Iterator<GameObject> iterator() {
        return new LevelIterator();
    }

    /**
     * @description: This is the class of iterator of iterable class Level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 23:48 given
     * @version: 1.0.0
     **/


    public class LevelIterator implements Iterator<GameObject> {

        int column = 0;
        int row = 0;

        /**
         * Override
         *
         * @param
         * @return boolean
         * @description: check if this level iterator has next element. Return 1, if it has; 0, otherwise
         * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
         * @date: 2020/11/19 22:56
         * @version:
         **/


        @Override
        public boolean hasNext() {
            return !(row == objectsGrid.ROWS - 1 && column == objectsGrid.COLUMNS);
        }

        /**
         * Override
         *
         * @param
         * @return com.ae2dms.GameObject
         * @description: get and move the iterator to the next game object in the level
         * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
         * @date: 2020/11/19 22:58
         * @version:
         **/

        @Override
        public GameObject next() {
            if (column >= objectsGrid.COLUMNS) {
                column = 0;
                row++;
            }
            GameObject object = objectsGrid.getGameObjectAt(column, row);
            GameObject diamond = diamondsGrid.getGameObjectAt(column, row);
            GameObject retObj = object;
            column++;
            if (diamond == GameObject.DIAMOND) {
                if (object == GameObject.CRATE) {
                    retObj = GameObject.CRATE_ON_DIAMOND;
                } else if (object == GameObject.FLOOR) {
                    retObj = diamond;
                } else {
                    retObj = object;
                }
            }
            return retObj;
        }

        /**
         * @param
         * @return java.awt.Point
         * @description: get the Point specified in LevelIterator
         * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
         * @date: 2020/11/19 22:27 given
         * @version:
         **/

        public Point getCurrentPosition() {
            return new Point(column, row);
        }
    }
}