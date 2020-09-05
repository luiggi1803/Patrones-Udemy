package construccion.singleton;

import java.nio.file.Files;

class StaticBlockSingleton {

    private StaticBlockSingleton() throws Exception {
        System.out.println("Singleton is initializing");
        Files.createTempFile(".", ".");
    }

    private static StaticBlockSingleton instance;

    static {

        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            System.out.println("Failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}


public class BlockStaticSingleton {

    public static void main(String[] args) {
        StaticBlockSingleton singleton = StaticBlockSingleton.getInstance();

    }
}
