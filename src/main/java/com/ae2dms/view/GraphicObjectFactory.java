package com.ae2dms.view;

import com.ae2dms.GameObject;
import com.ae2dms.model.GameEngine;
import javafx.scene.shape.Rectangle;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 * class name: GraphicObjectFactory
 *
 * @description: the factory of graphic object
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/21 15:22
 */
public class GraphicObjectFactory {

    /**
     * @param gameObject
     *         the game object in this game
     * @return javafx.scene.shape.Rectangle
     * @description: create the graphic game view of game object
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/21 20:57
     * @version: 1.1.0
     **/

    public Rectangle getGraphicObject(GameObject gameObject) {
        switch (gameObject) {
            case WALL:
                //return FXMLLoader.load(getClass().getResource("/view/Wall.fxml"));
                if (GameEngine.isDebugActive()) {
                    return new Wall(true);
                } else {
                    return new Wall();
                }
            case CRATE:
                //return FXMLLoader.load(getClass().getResource("/view/Crate.fxml"));
                if (GameEngine.isDebugActive()) {
                    return new Crate(true);
                } else {
                    return new Crate();
                }
            case DIAMOND:
                //return FXMLLoader.load(getClass().getResource("/view/Diamond.fxml"));
                if (GameEngine.isDebugActive()) {
                    return new Diamond(true);
                } else {
                    return new Diamond();
                }
            case KEEPER:
                //return FXMLLoader.load(getClass().getResource("/view/Keeper.fxml"));
                if (GameEngine.isDebugActive()) {
                    return new Keeper(true);
                } else {
                    return new Keeper();
                }
            case FLOOR:
                //return FXMLLoader.load(getClass().getResource("/view/Floor.fxml"));
                if (GameEngine.isDebugActive()) {
                    return new Floor(true);
                } else {
                    return new Floor();
                }
            case CRATE_ON_DIAMOND:
                //return FXMLLoader.load(getClass().getResource("/view/CrateOnDiamond.fxml"));
                if (GameEngine.isDebugActive()) {
                    return new CrateOnDiamond(true);
                } else {
                    return new CrateOnDiamond();
                }
            default:
                String message = "Error in Level constructor. Object not recognized.";
                GameEngine.logger.severe(message);
                throw new AssertionError(message);
        }
    }
}
