package com.ae2dms.controller;

import com.ae2dms.model.MarkKeeper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/3 18:15
 */
public class MarkBoardController {
    public ComboBox<String> mapSelectionComboBox;
    public GridPane markBoard;
    public AnchorPane pagePane;
    public Text noRecordPrompt;
    private ArrayList<MarkKeeper> markKeepers;
    private String DEFAULT_MAP_NAME = "Example Game!";
    private final int NUMBER_OF_SHOWN_ITEM = 5;

    public void initialize() throws IOException {
        readMarks();
        initializeComboBox();
        if (markKeepers!=null){
            mapSelectionComboBox.getSelectionModel().selectedItemProperty().addListener(
                    new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String oldItem, String newItem) {
                            setMarkBoardForMap(newItem);
                        }
                    }
            );
            setMarkBoardForMap(DEFAULT_MAP_NAME);
        }else {
            noRecordPrompt.visibleProperty().setValue(true);
        }

    }

    private void readMarks() throws IOException {
        Type REVIEW_TYPE = new TypeToken<List<MarkKeeper>>() {
        }.getType();
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(String.valueOf(getClass().getClassLoader().getResource("rank/ranking.json").getFile()));
        markKeepers = gson.fromJson(fileReader, REVIEW_TYPE);
        fileReader.close();
    }


    private void initializeComboBox() {
        ObservableList<String> comboBoxOptions = FXCollections.observableArrayList();
        if (markKeepers == null){
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
            addTextToGrid(markKeeperContent.userName, 1, count+1);
            addTextToGrid(String.valueOf(markKeeperContent.movesCount),2, count+1);
        }

    }

    private void addTextToGrid(String text, int column, int row) {
        Text textContent = new Text(text);
        markBoard.add(textContent, column, row);
    }

}
