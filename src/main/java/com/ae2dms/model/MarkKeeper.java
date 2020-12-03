package com.ae2dms.model;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 *
 * @description: TODO
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/2 15:28
 */
public class MarkKeeper {
    public String mapName;
    public String userName;
    public int movesCount;

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
    }


    public MarkKeeper(String mapName, String userName, int movesCount) {
        this.mapName = mapName;
        this.userName = userName;
        this.movesCount = movesCount;
    }
}
