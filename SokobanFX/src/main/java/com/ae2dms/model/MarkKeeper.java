package com.ae2dms.model;

/**
 * The project of AE2DMS Coursework of Yizirui FANG 20127091
 * <p>
 * Package: com.ae2dms.model
 * <p>
 * The class to store mark record information for a user
 *
 * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
 * @date: 2020/12/2 15:28
 */
public class MarkKeeper implements MarkKeeperComparable {
    public String mapName;
    public String userName;
    public int movesCount;


    /**
     * This constructor will set the input map name, user name and moves count to the field of this class
     *
     * @param mapName
     *         The name of the current game map
     * @param userName
     *         The user name input by the user
     * @param movesCount
     *         The total moves counts of the current map
     * @author: Yizirui FANG ID: 20127091 Email: scyyf1@nottingham.edu.cn
     * @date: 2020/12/6 17:15
     * @version: 1.0.0
     **/


    public MarkKeeper(String mapName, String userName, int movesCount) {
        this.mapName = mapName;
        this.userName = userName;
        this.movesCount = movesCount;
    }


    /**
     * Compares this object, game playing record, with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>This methods  ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>This methods must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, this methods ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param compareMarkKeeper
     *         the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException
     *         if the specified object is null
     * @throws ClassCastException
     *         if the specified object's type prevents it
     *         from being compared to this object.
     */
    @Override
    public int compareTo(MarkKeeper compareMarkKeeper) {
        return this.movesCount - compareMarkKeeper.movesCount;
    }
}
