package com.ae2dms.model;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.controller
 * <p>
 * This is the enum class for the limited amount of game objects involved in this game
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/10 15:43
 */
public enum GameObject {
    /**
     * the wall object in sokoban game, where the player could not go through
     */
    WALL('W'),
    /**
     * the floor object in sokoban game, which could be walked through by the player
     */
    FLOOR(' '),
    /**
     * the crate object in sokoban game, which need to be moved to the diamond position
     */
    CRATE('C'),
    /**
     * the diamond object in sokoban game, where the crate need to be carried to
     */
    DIAMOND('D'),
    /**
     * the keeper object in sokoban game, which is the main player of the game
     */
    KEEPER('S'),
    /**
     * the crate object in sokoban game, where the crate is moved to the target diamond
     */
    CRATE_ON_DIAMOND('O'),
    DEBUG_OBJECT('=');

    public final char symbol;

    /**
     * set the character which is the symbol of the game object to this GameObject class
     *
     * @param symbol
     *         the symbol of this class
     * @return com.ae2dms.model.GameObject
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/20 14:13 given
     * @version: 1.0.0
     **/

    GameObject(final char symbol) {
        this.symbol = symbol;
    }

    /**
     * return the game object whose symbol is the input character if the input character is value of game object; return WALL game object, otherwise
     *
     * @param checkedSymbol
     *         the character to check if this is one of possible values of game objects
     * @return com.ae2dms.model.GameObject
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/20 14:16 given
     * @version: 1.0.0
     **/

    public static GameObject fromChar(char checkedSymbol) {
        for (GameObject t : GameObject.values()) {
            if (Character.toUpperCase(checkedSymbol) == t.symbol) {
                return t;
            }
        }
        return WALL;
    }


    /**
     * get the symbol of this game object as string
     *
     * @return char
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/20 14:39 given
     * @version: 1.0.0
     **/


    public char getCharSymbol() {
        return symbol;
    }
}