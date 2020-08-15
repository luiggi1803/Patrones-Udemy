package construccion.singleton;

class LazySingleton {

    private LazySingleton() {
        System.out.println("Initializing a lazy singleton");
    }

    private static LazySingleton instance;

//    public static synchronized  LazySingleton getInstance() {
//        if (instance == null) {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    // double-check locking
    public static  LazySingleton getInstance() {

        if (instance==null){
            synchronized (LazySingleton.class){
                if (instance==null){
                    instance=new LazySingleton();
                }
            }
        }
        return instance;
    }


}

public class LazySingletonDemo {
}
