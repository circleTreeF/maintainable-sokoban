package com.ae2dms.view;
/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.view
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/14 22:27
 */

import com.ae2dms.controller.MenuBarController;
import javafx.scene.control.RadioMenuItem;


/**
 * @PackageName: com.ae2dms.view
 * @ClassName: RadioMenuItemDebug
 * @Description:
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @data 11, 14, 2020 22:27
 */
public class RadioMenuItemDebug extends RadioMenuItem {
    public RadioMenuItemDebug() {
        super("Toggle Debug");
        //TODO: check if the gameEngineer in the field is correct
        MenuBarController controller = new MenuBarController();
        this.setOnAction(actionEvent -> controller.toggleDebug());
    }
}
