package com.ae2dms.model;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 *
 * @description: abstract class attaching on the subject, notified when the subject, GameObject is modified.
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/29 1:55
 */
public abstract class Observer {
    //the subject to be observed
    public GameEngine gameEngine;

    /**
    * Update the value in observers
    * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
    * @date: 2020/11/29 17:17
     * @param
    * @return void
    * @version:
    **/


    public abstract void update();
}
