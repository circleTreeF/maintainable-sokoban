package com.ae2dms.model;

import com.ae2dms.GameObject;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 *
 * @description: This is the game engine
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/9 21:10 given
 * @version: 1.0
 */

public class GameEngine {
    public static final String GAME_NAME = "SokobanFX";
    private GameLoggerSingleton logger;
    //FIXME: should movesCount be statics or final?
    public int movesCount = 0;
    public String mapSetName;
    private static boolean debug = false;
    private Level currentLevel;
    private IteratorInterface iterator;
    private boolean gameComplete = false;
    private MovementTracker movementTracker;

    /**
     * constructor
     *
     * @param inputGameFile
     *         the input game file about the game map
     * @param production
     * @description: the constructor would load the game map file name stored in inputGameFile
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:26 given
     * @version: 1.0.0
     **/

    public GameEngine(InputStream inputGameFile, boolean production) {
        try {
            logger = GameLoggerSingleton.getGameLoggerSingleton();
            Map map = new Map(inputGameFile);
            iterator = map.getIterator();
            mapSetName = map.mapSetName;
            currentLevel = getNextLevel();
            movementTracker = new MovementTracker();
        } catch (IOException x) {
            System.out.println("Cannot create logger.");
        } catch (NoSuchElementException e) {
            logger.warning("Cannot load the default save file: " + e.getStackTrace());
        }
    }

    /**
     * @param
     * @return boolean
     * @description: check if the game is in debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 22:00
     * @version:
     **/


    public static boolean isDebugActive() {
        return debug;
    }

    /**
     * @param pressedKeyCode
     *         the key code of the pressed key in the keyboard
     * @return void
     * @description: handle the keyboard input, if the input is up, right, down, or left, move the character accordingly. if not do default
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:25 given
     * @version: 1.0.0
     **/

    //TODO: refactor switch statement
    //TODO: use a adapter design pattern to refactor the input variable in KeyCode data type to Event data type for the key combination
    public void handleKey(KeyCode pressedKeyCode) {
        UserDefinedKey userDefinedKey = new UserDefinedKey();
        switch (pressedKeyCode) {
            case UP:
                move(new Point(-1, 0));
                break;

            case RIGHT:
                move(new Point(0, 1));
                break;

            case DOWN:
                move(new Point(1, 0));
                break;

            case LEFT:
                move(new Point(0, -1));
                break;

            default:
                // TODO: implement something funny.
        }

        //TODO: refactor the if statement
        if (isDebugActive()) {
            System.out.println(pressedKeyCode);
        }
    }

    /**
     * @param delta
     *         the difference of location between current location and the destination in Point format about the movement
     * @return void
     * @description: move the keeper according to input movement direction and distance, and store the level before moving
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:26 given
     * @version: 1.0.0
     **/

    public void move(Point delta) {
        //TODO:refactor to state pattern
        if (isGameComplete()) {
            return;
        }

        Point keeperPosition = currentLevel.getKeeperPosition();
        GameObject keeper = currentLevel.objectsGrid.getGameObjectAt(keeperPosition);
        Point targetObjectPoint = GameGrid.translatePoint(keeperPosition, delta);
        GameObject keeperTarget = currentLevel.objectsGrid.getGameObjectAt(targetObjectPoint);

        //TODO: refactor the if statement
        if (GameEngine.isDebugActive()) {
            System.out.println("Current level state:");
            System.out.println(currentLevel.toString());
            System.out.println("Keeper pos: " + keeperPosition);
            System.out.println("Movement source obj: " + keeper);
            System.out.printf("Target object: %s at [%s]", keeperTarget, targetObjectPoint);
        }

        boolean keeperMoved = false;
        //FIXME: refactor the switch statement
        switch (keeperTarget) {

            case WALL:
                break;

            case CRATE:

                GameObject crateTarget = currentLevel.getTargetObject(targetObjectPoint, delta);
                if (crateTarget != GameObject.FLOOR) {
                    break;
                }

                movementTracker.trackerMove(currentLevel);

                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(targetObjectPoint, delta)), targetObjectPoint);
                currentLevel.objectsGrid.putGameObjectAt(keeperTarget, GameGrid.translatePoint(targetObjectPoint, delta));
                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(keeperPosition, delta)), keeperPosition);
                currentLevel.objectsGrid.putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, delta));
                keeperMoved = true;
                break;

            case FLOOR:
                movementTracker.trackerMove(currentLevel);

                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(keeperPosition, delta)), keeperPosition);
                currentLevel.objectsGrid.putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, delta));
                keeperMoved = true;
                break;

            default:
                logger.severe("The object to be moved was not a recognised GameObject.");
                throw new AssertionError("This should not have happened. Report this problem to the developer.");
        }

        //TODO: refactor the if statement
        if (keeperMoved) {
            keeperPosition.translate((int) delta.getX(), (int) delta.getY());
            movesCount++;
            if (currentLevel.isComplete()) {
                if (isDebugActive()) {
                    System.out.println("Level complete!");
                }

                currentLevel = getNextLevel();
            }
        }
    }


    /**
     * revoke the latest movement of user， the shortcut key for this function is ctrl + z set in GamePage.fxml
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 21:36
     * @version: 1.0.0
     **/


    public void undo() {
        currentLevel = movementTracker.trackerPop();
    }


    /**
     * @param
     * @return boolean
     * @description: return boolean value of if the game is completed
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 22:27
     * @version: 1.0.0
     **/


    public boolean isGameComplete() {
        return gameComplete;
    }

    /**
     * @param
     * @return com.ae2dms.model.Level
     * @description move to next level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:10 given
     * @version: 1.0.0
     **/

    //FIXME: the second level would be missed
    public Level getNextLevel() {

        //TODO: refactor the position of assigning variable $gameComplete$ in this class
        if (!iterator.hasNext()) {
            gameComplete = true;
        }
        return (Level) iterator.next();
    }

    /**
     * @param
     * @return com.ae2dms.model.Level
     * @description: return the current level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:22 given
     * @version: 1.0.0
     **/

    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * @param
     * @return void
     * @description: start or close the debug mode
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:23 given
     * @version: 1.0.0
     **/

    public void toggleDebug() {
        debug = !debug;
    }
}