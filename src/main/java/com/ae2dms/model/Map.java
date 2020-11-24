package com.ae2dms.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms
 *
 * @description: This class is a concrete class implemented to store the collection of levels of the game project
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/23 22:18
 */

public class Map implements ContainerInterface {
    private GameLoggerSingleton logger;
    public String mapSetName;
    private final List<Level> levels;

    public Map(InputStream inputGameFile) {
        try {
            logger = GameLoggerSingleton.getGameLoggerSingleton();
        } catch (IOException e) {
            e.printStackTrace();
        }
        levels = loadGameFile(inputGameFile);
    }

    /**
     * This method would load the map stored in the input parameter inputGameFile.
     *
     * @param inputGameFile
     *         the input file stream about the map of the game
     * @return java.util.List<com.ae2dms.model.Level>
     * the array list of map of all levels
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/24 12:10
     * @version:
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
     * return the iterator of Game Object under Iterator Pattern
     *
     * @return java.util.Iterator
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/22 22:42
     * @version: 1.0.0
     **/


    @Override
    public IteratorInterface getIterator() {
        return new LevelsIterator();
    }


    /**
     * This inner class is the iterator class for the Map class. This class implements the interface IteratorInterface
     *
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/24 14:26
     * @version:
     **/

    private class LevelsIterator implements IteratorInterface {
        // the index of the current level in the whole collection of levels
        int index = 0;

        /**
         * This method narrates navigation. This method returns true, if there is next element in the repository; false, otherwise.
         *
         * @return boolean
         * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
         * @date: 2020/11/22 22:44
         * @version: 1.0.0
         **/
        @Override
        public boolean hasNext() {
            return index < levels.size();
        }

        /**
         * get and move the iterator to the next element in the repository
         *
         * @return com.ae2dms.GameObject
         * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
         * @date: 2020/11/22 22:47
         * @version: 1.0.0
         **/
        @Override
        public Level next() {
            if (this.hasNext()) {
                return levels.get(index++);
            }
            return null;
        }
    }
}
