package com.ae2dms.view;/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:06
 */

import com.ae2dms.controller.MenuBarController;
import javafx.scene.control.MenuItem;

/**
 * @PackageName: com.ae2dms.view
 * @ClassName: MenuItemLoadGame
 * @Description:
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @data 11, 14, 2020 22:06
 */
public class MenuItemLoadGame extends MenuItem{
    public MenuItemLoadGame(){
        super("Load Game");
        MenuBarController controller = new MenuBarController();
        this.setOnAction(actionEvent -> controller.loadGame());
    }
}
