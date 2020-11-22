package com.ae2dms.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: The view of new dialog window without dialog message effect
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/13 22:33
 * @version: 1.0
 */
public class DialogWindow extends Stage {

    /**
     * constructor
     *
     * @param primaryStage
     *         the primary stage of this scene
     * @param dialogTitle
     *         the title of this dialog new window
     * @param dialogMessage
     *         the dialog message of this new dialog window
     * @description: construct a instance of dialog window without visually present
     * extracted from Main.java.newDialog
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/13 23:16
     * @version: 1.0.0
     **/

    //TODO: could be refactored to the factory design pattern
    public DialogWindow(Stage primaryStage, String dialogTitle, String dialogMessage, Effect dialogMessageEffect) {
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(primaryStage);
        this.setResizable(false);
        this.setTitle(dialogTitle);

        Text text1 = new Text(dialogMessage);
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setFont(javafx.scene.text.Font.font(14));

        //TODO: try to refactor the if statement
        if (dialogMessageEffect != null) {
            text1.setEffect(dialogMessageEffect);
        }

        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setBackground(Background.EMPTY);
        dialogVbox.getChildren().add(text1);

        Scene dialogScene = new Scene(dialogVbox, 350, 150);
        this.setScene(dialogScene);
    }
}

