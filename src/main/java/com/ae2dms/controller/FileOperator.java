package com.ae2dms.controller;

import com.ae2dms.model.GameEngine;
import com.ae2dms.model.GameLoggerSingleton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/28 18:03
 */
public class FileOperator {
    FileChooser fileChooser;
    GameLoggerSingleton logger;

    public FileOperator() {
        fileChooser = new FileChooser();
        try {
            logger = GameLoggerSingleton.getGameLoggerSingleton();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the file path and name according to the user selection
     * extracted from GamePageController.loadGameFile()
     *
     * @param loadedStage
     *         the stage where the file chooser window should based on
     * @return java.io.File
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/28 18:17
     * @version:
     **/

    public File selectGameFile(Stage loadedStage) {
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
        File saveFile = fileChooser.showOpenDialog(loadedStage);
        if (saveFile != null) {
            if (GameEngine.isDebugActive()) {
                logger.info("Loading save file: " + saveFile.getName());
            }
        }
        //TODO: perform something fun when the file is not chosen
        return saveFile;
    }

    /**
     * Return the file to save the game state specification file. Allow user to specify this file name and path.
     *
     * @param loadedStage
     *         the stage where the file chooser window should based on
     * @return java.io.File
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/28 20:01
     * @version:
     **/


    public File selectSaveGamePath(Stage loadedStage) {
        fileChooser.setTitle("Select Save Location:");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Select saved location", "*.skbSaved"));
        File savedLocation = fileChooser.showSaveDialog(loadedStage);
        //TODO: do something fun if the user select nothing/cancel
        return savedLocation;
    }

    /**
     * Return the game state specification file. Allow user to select the file in GUI file chooser
     *
     * @param loadStage
     *         the stage where the file chooser window should based on
     * @return java.io.File
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/28 20:25
     * @version:
     **/


    public File selectSavedGame(Stage loadStage) {
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Saved game file", "*.skbSaved"));
        File loadedFile = fileChooser.showOpenDialog(loadStage);
        //TODO: do something fun if the user select nothing/cancel
        return loadedFile;
    }
}
