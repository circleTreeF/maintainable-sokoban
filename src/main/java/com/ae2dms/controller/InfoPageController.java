package com.ae2dms.controller;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 *
 * @description: TODOa
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/5 22:15
 */
public class InfoPageController {
    @FXML
    public ImageView backwardIcon;
    @FXML
    public ImageView forwardIcon;
    @FXML
    public TextField textFieldURL;
    @FXML
    public WebView webViewWidget;
    public ImageView search_icon;
    public ImageView viewIcon;
    private WebEngine webEngine;
    private final String infoHomePage = "http://cslinux.nottingham.edu.cn/~scyyf1/dms/src/index.html";


    /**
     * This method is to initialize and will be called automatically when the information page is loaded. This method will load the homepage, the information page of this game and set the event listen on the URL text field {@code textFieldURL}, search icon{@code search_icon}, forward icon {@code forwardIcon}, backward icon{@code backwardIcon}, and view icon{@code viewIcon}.
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 1:30
     * @version: 1.0.0
     **/

    public void initialize() {
        webEngine = webViewWidget.getEngine();
        webEngine.load(infoHomePage);
        setTextFieldURLListener();
    }

    /**
     * set the event handler on the event, {@code MouseClicked} for backward icon, {@code backwardIcon}. This handler will set the web to the previous one in the history.
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 1:34
     * @version: 1.0.0
     **/


    public void onBackwardClicked() {
        webEngine.getHistory().go(-1);
    }

    /**
     * set the event handler on the event, {@code MouseClicked} for forward icon, {@code forwardIcon}. This handler will set the web to the forward one in the history.
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 1:36
     * @version: 1.0.0
     **/

    public void onForwardClicked() {
        webEngine.getHistory().go(1);
    }


    /**
     * set the event listener on the web engine of this web browser. This listener will deal with the web engine state change of the web browser.
     * <p>When the web engine works successfully, the text field will be changed according to the current website URL</p>
     * <p>Otherwise, the text file of URL will prompt the error information</p>
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 1:38
     * @version: 1.0.0
     **/

    private void setTextFieldURLListener() {
        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                WebHistory webHistory = webEngine.getHistory();
                textFieldURL.setText(webEngine.getLocation());
                backwardIcon.setDisable(webEngine.getHistory().getCurrentIndex() <= 0);
                forwardIcon.setDisable(webEngine.getHistory().getCurrentIndex() == webHistory.getEntries().size() - 1);
            } else if (newState == Worker.State.FAILED) {
                textFieldURL.setText("ERROR in " + webEngine.getLoadWorker().getException().getMessage());
            }
        });
    }


    /**
     * This method is to set the action of the event {@code MouseClicked} for the search icon of this web browser. When the search icon is clicked, the web browser will load the URL inputted in the URL text field.
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 1:43
     * @version: 1.0.0
     **/

    public void onSearchClicked() {
        webEngine.load(textFieldURL.textProperty().get());
    }


    /**
     * This method is to set the action of the event {@code MouseClicked} for the view icon of this web browser. When the view icon is clicked, the web browser will load the project repository page hosted in University GitLab.
     *
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 1:45
     * @version:
     **/


    public void onViewIconClicked() {
        String projectRepo = "https://csprojects.nottingham.edu.cn/scyyf1/ae2dms-cw-20127091";
        webEngine.load(projectRepo);
    }
}
