package com.ae2dms.model;

import com.google.gson.Gson;

import java.util.Stack;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 * <P>A <B>MovementTracker</B> object is used to store a collection of levels. The user can add level to this class to store and can also get the latest stored level.
 *
 * </P>
 *
 * <p>
 * When a stack is first created, it contains no items.
 * </P>
 *
 * @version 1.0.0
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/25 21:01
 */


public class MovementTracker {
    private final Stack<Level> movingLevelsStack;
    private final Gson gson = new Gson();

    /**
     * Constructor
     * <p>
     * construct the instance of this class without adding any element
     *
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/10 21:30
     * @version: 1.0.0
     **/

    public MovementTracker() {
        movingLevelsStack = new Stack<>();
    }


    /**
     * Stores an item onto the top of this tracker.
     *
     * @param currentLevel
     *         the level to be store onto the top of this tracker
     * @return void
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 21:31
     * @version: 1.0.0
     **/


    public void trackerMove(Level currentLevel) {
        Level copiedCurrentLevel = gson.fromJson(gson.toJson(currentLevel), Level.class);
        movingLevelsStack.push(copiedCurrentLevel);
    }

    /**
     * Removes the level at the top of this tracker and returns that level as the value of this function.
     *
     * @param
     * @return com.ae2dms.model.Level
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/25 21:32
     * @version: 1.0.0
     **/


    public Level trackerPop() {
        //TODO: do something fun when the stack is empty
        return movingLevelsStack.pop();
    }

    /**
    * Empty the tracker and return the element pushed into the track at the earliest time
    * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
    * @date: 2020/11/26 16:26
     * @param
    * @return com.ae2dms.model.Level
    * @version:
    **/


    public Level resetTrack(){
        if (!movingLevelsStack.empty()){
            Level firstElement = movingLevelsStack.get(0);
            movingLevelsStack.clear();
            return firstElement;
        } else {
            return  null;
        }
    }


}
