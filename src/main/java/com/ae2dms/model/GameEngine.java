package com.ae2dms.model;

import com.ae2dms.GameObject;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

public class GameEngine implements Serializable {
    private static final long serialVersionUID = 101L;
    public static final String GAME_NAME = "SokobanFX";
    private transient GameLoggerSingleton logger;
    //FIXME: should movesCount be statics or final?
    private int movesCount = 0;
    public String mapSetName;
    private static boolean debug = false;
    private Level currentLevel;
    public Map map;
    private IteratorInterface iterator;
    private boolean gameComplete = false;
    private MovementTracker movementTracker;
    private List<Observer> observers = new ArrayList<>();

    /**
    * get the state in this class, movesCount
    * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
    * @date: 2020/11/30 0:11
     * @param
    * @return int
    * @version: 1.0.0
    **/


    public int getMovesCount() {
        return movesCount;
    }


    public void setMovesCount(int newMoveCount) {
        this.movesCount = newMoveCount;
        notifyAllObservers();
    }


    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * initialize the instance of this class when deserializing. Assign default value to those field variables declared as transient
     *
     * @param inputStream
     *         the serialized input stream
     * @return void
     * @throws IOException
     *         Any of the usual Input/Output related exceptions.
     * @throws ClassNotFoundException
     *         Class of a serialized object cannot be found
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/28 16:01
     * @version: 1.0.0
     **/


    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        logger = GameLoggerSingleton.getGameLoggerSingleton();
    }


    /**
     * Non-argument constructor
     *
     * @param
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/28 14:33
     * @version:
     **/


    public GameEngine() {
        try {
            logger = GameLoggerSingleton.getGameLoggerSingleton();
            iterator = map.getIterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            map = new Map(inputGameFile);
            iterator = map.getIterator();
            mapSetName = map.mapSetName;
            currentLevel = getNextLevel();
            movementTracker = new MovementTracker();
            notifyAllObservers();
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
            int newMovesCount = movesCount +1;
            setMovesCount(newMovesCount);
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
        //TODO: when undo, the movesCount is set to be not changed by design
        currentLevel = movementTracker.trackerPop();
    }

    /**
     * reset the current level to the initial one, and reset the movesCount to 0
     *
     * @param
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/26 16:35
     * @version:
     **/

    public void resetCurrentLevel() {
        currentLevel = movementTracker.resetTrack();
        movesCount = 0;
    }


    /**
     * serialize this GameEngine class into the game state specification file and store this file in user-defined name and path
     *
     * @param savedLocation
     *         the path of the game state specification file should be stored at
     * @return void
     * @throws IOException
     *         Any of the usual Input/Output related exceptions.
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/28 16:26
     * @version:
     **/


    public void saveGame(File savedLocation) throws IOException {
        //TODO: store movesCount, map, movementTracker
        FileOutputStream fileOut = new FileOutputStream(savedLocation);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
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