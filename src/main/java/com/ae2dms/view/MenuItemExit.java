package com.ae2dms.view;/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:17
 */

import javafx.scene.control.MenuItem;

import static com.ae2dms.controller.MenuBarController.closeGame;

/**
 * @PackageName: com.ae2dms.view
 * @ClassName: MenuItemExit
 * @Description:
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @data 11, 14, 2020 22:17
 */
public class MenuItemExit extends MenuItem {
    public MenuItemExit(){
        super("Exit");
        this.setOnAction(actionEvent -> closeGame());
    }
}
