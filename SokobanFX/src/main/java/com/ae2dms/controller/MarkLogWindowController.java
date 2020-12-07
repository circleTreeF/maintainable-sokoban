package com.ae2dms.controller;

import com.ae2dms.model.GameEngine;
import com.ae2dms.model.MarkKeeper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.ae2dms.Main.gameEngine;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 * <p>
 * This class is the controller to the view page, mark log window. This page will pop up when the user completed a certain game map
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/2 21:31
 */
public class MarkLogWindowController {
    public Text mapMessage;
    public Text movesCountMessage;
    public ImageView saveAndBack;
    public TextField inputName;
    private String mapName;
    private Integer movesCount;

    /**
     * initialize the game completion page or the mark log page. Present the completed map name, the moves count to complete this map, and text field to prompt user to enter a user name
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 0:44
     * @version: 1.0.0
     **/

    public void initialize() {
        mapName = gameEngine.mapSetName;
        movesCount = gameEngine.previousLevelsMovesCountsProperty.getValue();
        mapMessage.setText(gameEngine.mapSetName);
        movesCountMessage.setText(String.valueOf(movesCount));
    }

    /**
     * save the users' game record on the specific map {@code gameEngine.mapSetName}, the moves count {@code movesCount}, and the user-inputted user name {@code String userName = inputName.getText()} in the serialized file, ranking.json in the rank directory of project resources path.
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 0:46
     * @version: 1.0.0
     **/


    public void onSaveAndBackClicked() throws IOException {
        String userName = inputName.getText();
        if (GameEngine.isDebugActive()) {
            System.out.println(userName);
        }
        Type REVIEW_TYPE = new TypeToken<List<MarkKeeper>>() {
        }.getType();
        Gson gson = new Gson();
        File recordsDir = new File(System.getProperty("user.dir") + "/rank");
        boolean isCreated = recordsDir.mkdirs();
        if (!isCreated) {
            System.out.println("Fail to create!");
        }
        File records = new File(recordsDir + "/ranking.json");
        if (!records.exists()) {
            boolean isCreate = records.createNewFile();
            if (GameEngine.isDebugActive()) {
                if (isCreate) {
                    System.out.println("Create the ranking record file");
                } else {
                    System.out.println("The ranking record file already exits");
                }
            }
        }

        FileReader fileReader = new FileReader(recordsDir + "/" + "ranking.json");
        ArrayList<MarkKeeper> markKeeper = gson.fromJson(fileReader, REVIEW_TYPE);
        fileReader.close();
        if (markKeeper == null) {
            markKeeper = new ArrayList<>();
        }
        markKeeper.add(new MarkKeeper(mapName, userName, movesCount));
        FileWriter fileWriter = new FileWriter(recordsDir + "/ranking.json");
        gson.toJson(markKeeper, fileWriter);
        fileWriter.close();
        Stage currentStage = (Stage) saveAndBack.getScene().getWindow();
        currentStage.close();
        BackToMain.back();
    }

}
