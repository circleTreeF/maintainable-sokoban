package com.ae2dms.model;

import java.io.Serializable;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 * <p>
 * This interface is about container. Concrete classes implementing this interface will be responsible to implement iterator interface and use it.
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/11/22 22:36
 */
public interface ContainerInterface extends Serializable {
    /**
     * return the iterator of Game Object under Iterator Pattern
     *
     * @return java.util.Iterator
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/11/22 22:42
     * @version: 1.0.0
     **/

    IteratorInterface getIterator();
}
