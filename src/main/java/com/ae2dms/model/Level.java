package com.ae2dms.model;

import java.awt.*;
import java.util.List;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 * <p>
 * This class is a concrete class implemented to store the collection of the game project of one level of the game
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/24 14:21
 */

public final class Level implements ContainerInterface {
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
     * the default constructor of class Level, write all GameObjects into objectsGrid where DIAMOND would be regraded as FLOOR; DIAMOND and FLOOR only into diamondsGrid
     *
     * @param levelName
     *         the name of the level, got from the map file
     * @param levelIndex
     *         the index of the level, count from 0 depending on the order of occurrence in the map file
     * @param rawLevel
     *         the list of raw level where game object is specified by strings, got from the map file
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/24 15:17
     * @version: 2.0.0
     **/


    public Level(String levelName, int levelIndex, List<String> rawLevel) {
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
            }
        }
    }

    /**
     * check if the current level of the game is completed by comparing the number of crate and the number of the crates already in the diamond position
     *
     * @return boolean
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
     * get the current position of keep in the current level
     *
     * @return java.awt.Point
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 17:28 given
     * @version: 1.0.0
     **/


    public Point getKeeperPosition() {
        return keeperPosition;
    }

    /**
     * get the game object transferred from source by delta
     *
     * @param source
     *         the source point
     * @param delta
     *         the offset for the source point to find the transferred point
     * @return com.ae2dms.model.GameObject
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 23:35 given
     * @version:
     **/


    public GameObject getTargetObject(Point source, Point delta) {
        return objectsGrid.getTargetFromSource(source, delta);
    }


    /**
     * get the name of the level
     *
     * @return java.lang.String
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 23:35 given
     * @version: 1.0.0
     **/


    public String getName() {
        return name;
    }


    /**
     * Return the string of objectsGrid of this class
     *
     * @return java.lang.String
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/24 15:25
     * @version: 1.0.0
     **/


    @Override
    public String toString() {
        return objectsGrid.toString();
    }

    /**
     * return the iterator of Game Object under Iterator Pattern
     *
     * @return java.util.Iterator
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/22 22:42
     * @version: 1.0.0
     **/
    @Override
    public LevelIterator getIterator() {
        return new LevelIterator();
    }

    /**
     * This inner class is the common iterator for both objectsGrid and diamondsGrid of this class
     *
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/24 15:26
     * @version: 2.0.0
     **/

    public class LevelIterator implements IteratorInterface {

        int column = 0;
        int row = 0;

        /**
         * Override
         * <p>
         * This method narrates navigation. This method returns true, if there is next element in the repository; false, otherwise.
         *
         * @return boolean
         * @description: * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
         * @date: 2020/11/19 22:56
         * @version: 1.0.0
         **/


        @Override
        public boolean hasNext() {
            return !(row == objectsGrid.ROWS - 1 && column == objectsGrid.COLUMNS);
        }

        /**
         * Override
         * <p>
         * Get the current game object and move the iterator to the next element in the repository
         *
         * @return com.ae2dms.model.GameObject
         * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
         * @date: 2020/11/22 22:47
         * @version: 1.1.0
         **/
        @Override
        public GameObject next() {
            if (column >= objectsGrid.COLUMNS) {
                column = 0;
                row++;
            }
            GameObject object = objectsGrid.getGameObjectAt(column, row);
            GameObject diamond = diamondsGrid.getGameObjectAt(column, row);
            GameObject currentObject = object;
            column++;
            if (diamond == GameObject.DIAMOND) {
                if (object == GameObject.CRATE) {
                    currentObject = GameObject.CRATE_ON_DIAMOND;
                } else if (object == GameObject.FLOOR) {
                    currentObject = diamond;
                }
            }
            return currentObject;
        }

        /**
         * get the Point specified in LevelIterator
         *
         * @return java.awt.Point
         * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
         * @date: 2020/11/19 22:27 given
         * @version: 1.0.0
         **/

        public Point getCurrentPosition() {
            return new Point(column, row);
        }
    }
}