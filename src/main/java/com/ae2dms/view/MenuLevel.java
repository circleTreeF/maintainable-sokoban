package com.ae2dms.view;
/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:30
 */

import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;

import static com.ae2dms.controller.MenuBarController.resetLevel;

/**
 * @PackageName: com.ae2dms.view
 * @ClassName: MenuLevel
 * @Description:
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @data 11, 14, 2020 22:30
 */
public class MenuLevel extends Menu {
    public MenuLevel() {
        super("Level");
        this.setOnAction(actionEvent -> resetLevel());
        this.getItems().addAll(new MenuItemUndo(), new RadioMenuItemMusic(), new RadioMenuItemDebug(),
                new SeparatorMenuItem(), new MenuItemResetLevel());
    }
}
