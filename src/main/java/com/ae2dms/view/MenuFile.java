package com.ae2dms.view;
/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:00
 */

import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;

/**
 * @PackageName: com.ae2dms.view
 * @ClassName: MenuFile
 * @Description:
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @data 11, 14, 2020 22:00
 */
public class MenuFile extends Menu {
    public MenuFile() {
        super("File");
        this.getItems().addAll(new MenuItemSaveGame(),new MenuItemLoadGame(), new SeparatorMenuItem(), new MenuItemExit());
    }

}
