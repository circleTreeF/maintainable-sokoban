package com.ae2dms.view;/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:34
 */

import com.ae2dms.controller.MenuBarController;
import javafx.scene.control.Menu;

/**
 * @PackageName: com.ae2dms.view
 * @ClassName: MenuAbout
 * @Description:
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @data 11, 14, 2020 22:34
 */
public class MenuAbout extends Menu {
    public MenuAbout(){
        super("About");
        //TODO: test if the controller has the correct primaryStage in field
        MenuBarController controller = new MenuBarController();
        this.setOnAction(actionEvent -> controller.showAbout());
        this.getItems().addAll(new MenuItemGame());
    }
}
