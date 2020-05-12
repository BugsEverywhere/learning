package indi.simon.learning.leetcode.tobereviewed;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class SingleTon {

    private static SingleTon uniqueObject = new SingleTon();

    public SingleTon() {
    }

    public static SingleTon getInstance() {
        return uniqueObject;
    }

}
