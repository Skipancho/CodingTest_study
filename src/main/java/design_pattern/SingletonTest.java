package design_pattern;

public class SingletonTest {
    private static SingletonTest st = new SingletonTest();

    private SingletonTest(){}

    public static SingletonTest getInstance(){
        return st;
    }
}
