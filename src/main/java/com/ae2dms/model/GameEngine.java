package com.ae2dms.model;

import com.ae2dms.GameGrid;
import com.ae2dms.GameObject;
import com.ae2dms.Level;

import javafx.scene.input.KeyCode;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class GameEngine {
    public static final String GAME_NAME = "SokobanFX";
    public static GameLoggerSingleton logger;
    //FIXME: should movesCount be statics or final?
    public int movesCount = 0;
    public String mapSetName;
    private static boolean debug = false;
    private Level currentLevel;
    private List<Level> levels;
    private boolean gameComplete = false;

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
            levels = loadGameFile(inputGameFile);
            currentLevel = getNextLevel();
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
     * @description: move the keeper according to input movement direction and distance
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

                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(targetObjectPoint, delta)), targetObjectPoint);
                currentLevel.objectsGrid.putGameObjectAt(keeperTarget, GameGrid.translatePoint(targetObjectPoint, delta));
                currentLevel.objectsGrid.putGameObjectAt(currentLevel.objectsGrid.getGameObjectAt(GameGrid.translatePoint(keeperPosition, delta)), keeperPosition);
                currentLevel.objectsGrid.putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, delta));
                keeperMoved = true;
                break;

            case FLOOR:
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
     * @param inputGameFile
     *         the input file stream about the map of the game
     * @return java.util.List<com.ae2dms.Level>
     *         the array list of map of all levels
     * @description This method would load the map stored in the input parameter inputGameFile.
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:06 given
     * @version: 1.0.0
     **/

    private List<Level> loadGameFile(InputStream inputGameFile) {
        List<Level> levels = new ArrayList<>(5);
        int levelIndex = 0;
        //FIXME: refactor to handle more proper exception, i.e. place the exception in a new class
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputGameFile))) {
            boolean parsedFirstLevel = false;
            List<String> rawLevel = new ArrayList<>();
            String levelName = "";

            //FIXME: avoid while (true)
            //read the lines in the input file to level
            while (true) {
                String line = reader.readLine();

                //FIXME: refactor to avoid if statement
                if (line == null) {
                    if (rawLevel.size() != 0) {
                        Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                        levels.add(parsedLevel);
                    }
                    break;
                }

                if (line.contains("MapSetName")) {
                    mapSetName = line.replace("MapSetName: ", "");
                    continue;
                }

                if (line.contains("LevelName")) {
                    if (parsedFirstLevel) {
                        Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
                        levels.add(parsedLevel);
                        rawLevel.clear();
                    } else {
                        parsedFirstLevel = true;
                    }

                    levelName = line.replace("LevelName: ", "");
                    continue;
                }

                //delete the space on the both sides
                line = line.trim();
                //upper case and lower case may all exist in the .skb
                line = line.toUpperCase();
                //TODO: W should also be set at the both sides of lines
                if (line.matches(".*W.*W.*")) {
                    rawLevel.add(line);
                }
            }
            //TODO: refactor the exception to the new classes of exception
        } catch (IOException e) {
            logger.severe("Error trying to load the game file: " + e);
        } catch (NullPointerException e) {
            logger.severe("Cannot open the requested file: " + e);
        }

        return levels;
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
     * @return com.ae2dms.Level
     * @description move to next level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 14:10 given
     * @version: 1.0.0
     **/

    //FIXME: the second level would be missed
    public Level getNextLevel() {

        if (currentLevel == null) {
            return levels.get(0);
        }
        int currentLevelIndex = currentLevel.getIndex();
        if (currentLevelIndex < levels.size()) {
            return levels.get(currentLevelIndex + 1);
        }
        //TODO: refactor the position of assigning variable $gameComplete$ in this class
        gameComplete = true;
        return null;
    }

    /**
     * @param
     * @return com.ae2dms.Level
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