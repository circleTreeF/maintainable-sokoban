package com.ae2dms.view;

import com.ae2dms.model.GameEngine;
import com.ae2dms.GameObject;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view.GraphicObject
 *
 * @description: This is the class to store the graphic game objects
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/10 11:10 given
 * @version: 1.0
 */

public class GraphicObject extends Rectangle {
    /**
     * @param gameObject
     *         The game object in this game to construct the graphic object
     * @description: the default constructor of class GraphicObject, would construct the graphic object according to the type of the game object
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/12 16:59
     * @version: 1.0.0
     **/

    //TODO: refactor to factory model, could be built by enum class

    public GraphicObject(GameObject gameObject) throws IOException {
        Paint color = Color.AQUA;
        switch (gameObject) {
            case WALL:
                color = Color.BLACK;
                break;

            case CRATE:
                color = Color.ORANGE;
                break;

            case DIAMOND:
                color = Color.DEEPSKYBLUE;

                if (GameEngine.isDebugActive()) {
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), this);
                    fadeTransition.setFromValue(1.0);
                    fadeTransition.setToValue(0.2);
                    fadeTransition.setCycleCount(Timeline.INDEFINITE);
                    fadeTransition.setAutoReverse(true);
                    fadeTransition.play();
                }

                break;

            case KEEPER:
                color = Color.RED;
                break;

            case FLOOR:
                color = Color.WHITE;
                break;

            case CRATE_ON_DIAMOND:
                color = Color.DARKCYAN;
                break;

            default:
                String message = "Error in Level constructor. Object not recognized.";
                GameEngine.logger.severe(message);
                throw new AssertionError(message);
        }

        this.setFill(color);
        this.setHeight(30);
        this.setWidth(30);
        //set the property of the round corners
        if (gameObject != GameObject.WALL) {
            this.setArcHeight(50);
            this.setArcWidth(50);
        }

        if (GameEngine.isDebugActive()) {
            this.setStroke(Color.RED);
            this.setStrokeWidth(0.25);
        }
    }
}
