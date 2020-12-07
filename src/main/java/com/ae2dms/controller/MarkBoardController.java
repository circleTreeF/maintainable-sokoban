package com.ae2dms.controller;

import com.ae2dms.model.MarkKeeper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 * <p>
 * his class includes controller for {@code MarkBoard.fxml}, which is the view page for the game history record
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/3 18:15
 */
public class MarkBoardController {
    public ComboBox<String> mapSelectionComboBox;
    public GridPane markBoard;
    public AnchorPane pagePane;
    public Text noRecordPrompt;
    public ImageView backButton;
    private ArrayList<MarkKeeper> markKeepers;
    private final String DEFAULT_MAP_NAME = "Example Game!";
    private final int NUMBER_OF_SHOWN_ITEM = 5;

    /**
     * initialize the mark board page. This page will present the game records and combobox for map selection. By default, the default map records will be present.
     * <p>If there is not any record of any map of this sokoban game, the warning text, "No Record" will be present </p>
     *
     * @return void
     * @throws IOException
     *         Signals that an I/O exception of some sort has occurred
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 1:01
     * @version: 1.0.0
     **/

    public void initialize() throws IOException {
        readMarks();
        initializeComboBox();
        if (markKeepers != null) {
            mapSelectionComboBox.getSelectionModel().selectedItemProperty().addListener(
                    (observableValue, oldItem, newItem) -> setMarkBoardForMap(newItem)
            );
            setMarkBoardForMap(DEFAULT_MAP_NAME);
        } else {
            noRecordPrompt.visibleProperty().setValue(true);
        }

    }


    /**
     * read the users' game record stored in the serialized file, ranking.json in the rank directory of project resources path.
     *
     * @return void
     * @throws IOException
     *         Signals that an I/O exception of some sort has occurred
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 1:08
     * @version: 1.0.0
     **/

    private void readMarks() throws IOException {
        File record = new File(System.getProperty("user.dir") + "/rank/ranking.json");
        if (record.exists()) {
            FileReader fileReader = new FileReader(record);
            Type reviewType = new TypeToken<List<MarkKeeper>>() {
            }.getType();
            Gson gson = new Gson();
            markKeepers = gson.fromJson(fileReader, reviewType);
            fileReader.close();
        }
    }


    /**
     * initialize the combo box of this mark board view page.
     * <p>If there is map record in the ranking file, all available map name stored in the ranking file will be present in this node.</p>
     * <p>Otherwise, the combo box will be disabled.</p>
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 1:09
     * @version: 1.0.0
     **/


    private void initializeComboBox() {
        ObservableList<String> comboBoxOptions = FXCollections.observableArrayList();
        if (markKeepers == null) {
            mapSelectionComboBox.setDisable(true);
            return;
        }
        for (MarkKeeper currentMark : markKeepers) {
            if (!comboBoxOptions.contains(currentMark.mapName)) {
                comboBoxOptions.add(currentMark.mapName);
            }
        }
        mapSelectionComboBox.itemsProperty().setValue(comboBoxOptions);
    }

    /**
     * present the history record of map {@code String mapName}, in the grid pane of this page. The record will be present in the follow order, user name, and moves counts.
     * <p>If the number of records of this map is more than 5, only 5 record with the smallest moves counts will be present.</p>
     * <p>Otherwise, all records of this map will be present.</p>
     *
     * @param mapName
     *         the name of map to be present in this scene
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 1:12
     * @version: 1.0.0
     **/


    private void setMarkBoardForMap(String mapName) {
        ArrayList<MarkKeeper> markKeepersForMap = new ArrayList<>(5);
        for (MarkKeeper currentMark : markKeepers) {
            if (currentMark.mapName.equals(mapName)) {
                markKeepersForMap.add(currentMark);
            }
        }
        Collections.sort(markKeepersForMap);
        ArrayList<MarkKeeper> shownMarkKeepersForMap = new ArrayList<>(5);
        if (markKeepersForMap.size() > NUMBER_OF_SHOWN_ITEM) {
            shownMarkKeepersForMap.addAll(markKeepersForMap.subList(0, NUMBER_OF_SHOWN_ITEM - 1));
        } else {
            shownMarkKeepersForMap.addAll(markKeepersForMap);
        }
        for (int count = 0; count < shownMarkKeepersForMap.size(); count++) {
            MarkKeeper markKeeperContent = shownMarkKeepersForMap.get(count);
            addTextToGrid(markKeeperContent.userName, 1, count + 1);
            addTextToGrid(String.valueOf(markKeeperContent.movesCount), 2, count + 1);
        }

    }


    /**
     * add the history record in {@code Text} to the grid pane of this scene
     *
     * @param text
     *         the {@code String} of the history records
     * @param column
     *         the column of this text should be present in the grid pane
     * @param row
     *         the row of this text should be present in the grid pane
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 1:15
     * @version: 1.0.0
     **/

    private void addTextToGrid(String text, int column, int row) {
        Text textContent = new Text(text);
        markBoard.add(textContent, column, row);
    }

    /**
     * This method will be called when the button, back, is clicked.
     * <p>This method is to set the scene back to the main dashboard scene of this game</p>
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/4 3:11
     * @version: 1.0.0
     **/


    public void onBackClicked() {
        BackToMain.back();
    }
}
