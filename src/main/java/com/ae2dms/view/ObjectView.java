package com.ae2dms.view;

import javafx.scene.shape.Rectangle;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * The abstract product, declares an abstract class for product objects
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/22 17:46
 */


public abstract class ObjectView extends Rectangle {
    public void initializeCommonSize() {
        this.setHeight(30);
        this.setWidth(30);
    }
}
