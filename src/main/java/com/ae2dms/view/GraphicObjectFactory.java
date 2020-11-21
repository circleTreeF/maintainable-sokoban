package com.ae2dms.view;

import com.ae2dms.GameObject;
import com.ae2dms.model.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 * class name: GraphicObjectFactory
 * @description: the factory of graphic object
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 15:22
 */
public class GraphicObjectFactory {

    public Rectangle getGraphicObject(GameObject gameObject) throws IOException {
        switch (gameObject) {
            case WALL:
                return FXMLLoader.load(getClass().getResource("/view/Wall.fxml"));
            case CRATE:
                return FXMLLoader.load(getClass().getResource("/view/Crate.fxml"));
            case DIAMOND:
                return FXMLLoader.load(getClass().getResource("/view/Diamond.fxml"));
            case KEEPER:
                return FXMLLoader.load(getClass().getResource("/view/Keeper.fxml"));
            case FLOOR:
                return FXMLLoader.load(getClass().getResource("/view/Floor.fxml"));
            case CRATE_ON_DIAMOND:
                return FXMLLoader.load(getClass().getResource("/view/CrateOnDiamond.fxml"));
            default:
                String message = "Error in Level constructor. Object not recognized.";
                GameEngine.logger.severe(message);
                throw new AssertionError(message);
        }
    }
}
