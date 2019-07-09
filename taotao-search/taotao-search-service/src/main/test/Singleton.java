/**
 * 静态内部类单例
 */
public class Singleton {

    private Singleton(){}

    public static final Singleton getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }

}
