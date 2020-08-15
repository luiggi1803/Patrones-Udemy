package construccion.singleton;

import java.io.*;

class BasicSingleton implements Serializable {

    private BasicSingleton() {
    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    protected Object readResolve() {
        return INSTANCE;
    }
}


public class Basic {

    static void saveToFile(BasicSingleton singleton, String filename) throws Exception {

        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            System.out.println(filename);
            out.writeObject(singleton);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static BasicSingleton readFromFile(String filename) throws Exception {

        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        // 1. Reflection
        // 2. Serialization

        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(500);

        String filename = System.getProperty("user.dir") + "\\" + "singleton.txt";
        saveToFile(singleton, filename);

        singleton.setValue(222);

        BasicSingleton singleton2 = readFromFile(filename);

        System.out.println(singleton == singleton2);
        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());
    }

}
