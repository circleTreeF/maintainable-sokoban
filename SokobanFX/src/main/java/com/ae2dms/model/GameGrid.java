package com.ae2dms.model;

import java.awt.*;
import java.io.Serializable;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model.GameGrid
 * This is the class to place the graphic objects of the game to present
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/10 11:10 given
 * @version: 1.0
 */

public class GameGrid implements Serializable {
    final int COLUMNS;
    final int ROWS;
    private final GameObject[][] gameObjects;

    /**
     * This constructor is the default constructor. This constructor will create a array of GameObject with specified number of columns and rows.
     * to initialize the array of GameObject storing the current level.
     *
     * @param columns
     *         the size of columns of the GameGrid
     * @param rows
     *         the size of rows of the GameGrid
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 23:30 given
     * @version: 1.0.0
     **/


    public GameGrid(int columns, int rows) {
        COLUMNS = columns;
        ROWS = rows;

        // Initialize the array
        gameObjects = new GameObject[COLUMNS][ROWS];
    }

    /**
     * translate the point {@code sourceLocation} by delta in x and y axis
     *
     * @param sourceLocation
     *         the source location of the game object
     * @param delta
     *         the delta value this source need to translate
     * @return java.awt.Point
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 23:14
     * @version: 1.0.0
     **/


    public static Point translatePoint(Point sourceLocation, Point delta) {
        Point translatedPoint = new Point(sourceLocation);
        translatedPoint.translate((int) delta.getX(), (int) delta.getY());
        return translatedPoint;
    }


    /**
     * get the game object transferred from source by delta
     *
     * @param source
     *         the point of the source point
     * @param delta
     *         the delta value of target from the source in Point format
     * @return com.ae2dms.model.GameObject
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 23:36 given
     * @version: 1.0.0
     **/


    GameObject getTargetFromSource(Point source, Point delta) {
        return getGameObjectAt(translatePoint(source, delta));
    }

    /**
     * check if the input col, cow is within the boundary and return the GameObject at the input position.
     * If no, throw exception and promote in console if in debug mode
     *
     * @param col
     *         the column index of the searched game object
     * @param row
     *         the row index of the searched game object
     * @return com.ae2dms.model.GameObject
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 22:30 given
     * @version: 1.0.0
     **/

    public GameObject getGameObjectAt(int col, int row) throws ArrayIndexOutOfBoundsException {
        if (isPointOutOfBounds(col, row)) {
            if (GameEngine.isDebugActive()) {
                System.out.printf("Trying to get null GameObject from COL: %d  ROW: %d", col, row);
            }
            throw new ArrayIndexOutOfBoundsException("The point [" + col + ":" + row + "] is outside the map.");
        }

        return gameObjects[col][row];
    }

    /**
     * return the game object at the input point
     *
     * @param p
     *         the point of the game object
     * @return com.ae2dms.model.GameObject
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 22:37 given
     * @version: 1.0.0
     **/

    public GameObject getGameObjectAt(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }

        return getGameObjectAt((int) p.getX(), (int) p.getY());
    }

    /**
     * put the game object at the defined position, return true if success; false, otherwise.
     *
     * @param gameObject
     *         the game object to be put
     * @param x
     *         the x value in the x axis to put the game object at
     * @param y
     *         the y value in the y axis to put the game object at
     * @return boolean
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/12 0:07 given
     * @version:
     **/

    public boolean putGameObjectAt(GameObject gameObject, int x, int y) {
        if (isPointOutOfBounds(x, y)) {
            return false;
        }

        gameObjects[x][y] = gameObject;
        return gameObjects[x][y] == gameObject;
    }

    /**
     * put the game object at the input point
     *
     * @param gameObject
     *         the game object to be put
     * @param p
     *         the point the game object should be put at
     * @return boolean
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 21:59 given
     * @version: 1.0.0
     **/


    public boolean putGameObjectAt(GameObject gameObject, Point p) {
        return p != null && putGameObjectAt(gameObject, (int) p.getX(), (int) p.getY());
    }

    /**
     * check if the input index is out of bound for this level
     *
     * @param x
     *         the x value in the x axis for the point need to be checked
     * @param y
     *         the y value in the y axis for the point need to be checked
     * @return boolean
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/12 0:01 given
     * @version: 1.0.0
     **/


    private boolean isPointOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= COLUMNS || y >= ROWS);
    }


    /**
     * return the string of all game object, separated with new line
     *
     * @return java.lang.String
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 23:27 given
     * @version: 1.0.0
     **/

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(gameObjects.length);

        for (GameObject[] gameObject : gameObjects) {
            for (GameObject currentGameObject : gameObject) {
                if (currentGameObject == null) {
                    currentGameObject = GameObject.DEBUG_OBJECT;
                }
                stringBuilder.append(currentGameObject.getCharSymbol());
            }

            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}