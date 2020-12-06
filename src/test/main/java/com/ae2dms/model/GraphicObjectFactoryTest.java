package com.ae2dms.model;

import com.ae2dms.GameObject;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 * <p>
 * The test of GraphicObjectFactory
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/6 19:37
 */
class GraphicObjectFactoryTest {
    private GraphicObjectFactory graphicObjectFactory;
    private JFXPanel panel =new JFXPanel();

    @BeforeEach
    public void setUp() {
        graphicObjectFactory = new GraphicObjectFactory();
    }

    @Test
    void getGraphicObject() {
            graphicObjectFactory.getGraphicObject(GameObject.FLOOR);
    }
}