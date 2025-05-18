package indi.simon.learning;

import java.util.ServiceLoader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ServiceLoader<MyInterface> myInterface = ServiceLoader.load(MyInterface.class);



        System.out.println("Hello World!".toCha);
    }
}
