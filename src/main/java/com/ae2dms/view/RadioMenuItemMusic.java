package com.ae2dms.view;/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:26
 */

import javafx.scene.control.RadioMenuItem;

import static com.ae2dms.controller.MenuBarController.toggleMusic;

/**
 * @PackageName: com.ae2dms.view
 * @ClassName: RadioMenuItemMusic
 * @Description:
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @data 11, 14, 2020 22:26
 */
public class RadioMenuItemMusic extends RadioMenuItem {
    public RadioMenuItemMusic() {
        super("Toggle Music");
        this.setOnAction(actionEvent -> toggleMusic());
    }
}
