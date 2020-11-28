package com.ae2dms.model;

import java.io.Serializable;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 *
 * @description: This interface will narrate navigation methods and will be implemented and used in the concrete classes.
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/22 22:34
 */
public interface IteratorInterface extends Serializable {
    /**
     * This method narrates navigation. This method returns true, if there is next element in the repository; false, otherwise.
     *
     * @param
     * @return boolean
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/22 22:44
     * @version: 1.0.0
     **/

    boolean hasNext();


    /**
     * get and move the iterator to the next element in the repository
     *
     * @param
     * @return com.ae2dms.GameObject
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/22 22:47
     * @version: 1.0.0
     **/

    Object next();
}
