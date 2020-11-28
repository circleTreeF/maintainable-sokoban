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
public class OperateFile {
    FileChooser fileChooser;
    GameLoggerSingleton logger;

    public OperateFile() {
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
     * @param loadStage
     *         the stage where the file chooser window should based on
     * @return java.io.File
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/28 18:17
     * @version:
     **/

    public File selectGameFile(Stage loadStage) {
        fileChooser.setTitle("Open Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
        File saveFile = fileChooser.showOpenDialog(loadStage);
        if (saveFile != null) {
            if (GameEngine.isDebugActive()) {
                logger.info("Loading save file: " + saveFile.getName());
            }
        }
        //TODO: perform something fun when the file is not chosen
        return saveFile;
    }

    public File selectSaveGamePath(Stage loadStage) {
        fileChooser.setTitle("Select Save Location:");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Select saved location", "*.skbSaved"));
        File savedLocation = fileChooser.showSaveDialog(loadStage);
        return savedLocation;
    }
}
