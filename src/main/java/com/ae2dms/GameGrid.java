package com.ae2dms;

import com.ae2dms.model.GameEngine;

import java.awt.*;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.GameGrid
 *
 * @description: This is the class to place the graphic objects of the game to present
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/10 11:10 given
 * @version: 1.0
 */

public class GameGrid {
    final int COLUMNS;
    final int ROWS;
    private final GameObject[][] gameObjects;

    /**
     * constructor
     *
     * @param columns
     *         the size of columns of the GameGrid
     * @param rows
     *         the size of rows of the GameGrid
     * @description: the default constructor, create a array of GameObject with specified number of columns and rows
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/19 23:30 given
     * @version: 1.0.0
     **/


    public GameGrid(int columns, int rows) {
        COLUMNS = columns;
        ROWS = rows;

        // Initialize the array
        //FIXME:the column and row is in the opposite
        gameObjects = new GameObject[COLUMNS][ROWS];
    }

    /**
     * static
     *
     * @param sourceLocation
     *         the source location of the game object
     * @param delta
     *         the delta value this source need to translate
     * @return java.awt.Point
     * @description: translate the point sourceLocation by delta in x and y axis
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 23:14
     * @version: 1.0.0
     **/


    public static Point translatePoint(Point sourceLocation, Point delta) {
        Point translatedPoint = new Point(sourceLocation);
        translatedPoint.translate((int) delta.getX(), (int) delta.getY());
        return translatedPoint;
    }

    public Dimension getDimension() {
        return new Dimension(COLUMNS, ROWS);
    }

    /**
     * @param source
     *         the point of the source point
     * @param delta
     *         the delta value of target from the source in Point format
     * @return com.ae2dms.GameObject
     * @description: get the game object transferred from source by delta
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 23:36 given
     * @version:
     **/


    GameObject getTargetFromSource(Point source, Point delta) {
        return getGameObjectAt(translatePoint(source, delta));
    }

    /**
     * @param col
     *         the column index of the searched game object
     * @param row
     *         the row index of the searched game object
     * @return com.ae2dms.GameObject
     * @description: check if the input col, cow is within the boundary and return the GameObject at the input position.
     * If no, throw exception and promote in console if in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 22:30 given
     * @version: 1.0.0
     **/

    public GameObject getGameObjectAt(int col, int row) throws ArrayIndexOutOfBoundsException {
        if (isPointOutOfBounds(col, row)) {
            if (GameEngine.isDebugActive()) {
                System.out.printf("Trying to get null GameObject from COL: %d  ROW: %d", col, row);
            }
            //TODO: exception may be generated to a new class
            throw new ArrayIndexOutOfBoundsException("The point [" + col + ":" + row + "] is outside the map.");
        }

        return gameObjects[col][row];
    }

    /**
     * Overload
     *
     * @param p
     *         the point of the game object
     * @return com.ae2dms.GameObject
     * @description: return the game object at the input point
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/11 22:37 given
     * @version: 1.0.0
     **/

    public GameObject getGameObjectAt(Point p) {
        if (p == null) {
            //TODO: all exception could be generated in a new class
            throw new IllegalArgumentException("Point cannot be null.");
        }

        return getGameObjectAt((int) p.getX(), (int) p.getY());
    }

    public boolean removeGameObjectAt(Point position) {
        return putGameObjectAt(null, position);
    }

    /**
     * @param gameObject
     *         the game object to be put
     * @param x
     *         the x value in the x axis to put the game object at
     * @param y
     *         the y value in the y axis to put the game object at
     * @return boolean
     * @description: put the game object at the defined position, return true if success; false, otherwise.
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
     * overload
     *
     * @param gameObject
     *         the game object to be put
     * @param p
     *         the point the game object should be put at
     * @return boolean
     * @description: put the game object at the input point
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 21:59 given
     * @version:
     **/


    public boolean putGameObjectAt(GameObject gameObject, Point p) {
        //TODO: make the style consist with others, implicit if statement for all or explicit
        return p != null && putGameObjectAt(gameObject, (int) p.getX(), (int) p.getY());
    }

    /**
     * @param x
     *         the x value in the x axis for the point need to be checked
     * @param y
     *         the y value in the y axis for the point need to be checked
     * @return boolean
     * @description: check if the input index is out of bound for this level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/12 0:01 given
     * @version: 1.0.0
     **/


    private boolean isPointOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= COLUMNS || y >= ROWS);
    }

    /**
     * @param p
     *         the point need to be checked
     * @return boolean
     * @description: check if the input index is out of bound for this level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/12 16:32
     * @version: 1.0.0
     **/


    private boolean isPointOutOfBounds(Point p) {
        return isPointOutOfBounds(p.x, p.y);
    }

    /**
     * @param
     * @return java.lang.String
     * @description: return the string of all game object, separated with new line
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