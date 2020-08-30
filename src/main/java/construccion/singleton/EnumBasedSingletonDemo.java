package construccion.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

enum EnumBasedSingleton {
    INSTANCE;

    EnumBasedSingleton() {
        valor=42;
    }

    private int valor;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}

public class EnumBasedSingletonDemo {

    static void saveToFile(EnumBasedSingleton singleton, String filename) throws Exception {

        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            System.out.println(filename);
            out.writeObject(singleton);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static EnumBasedSingleton readFromFile(String filename) throws Exception {

        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (EnumBasedSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        EnumBasedSingleton singleton= EnumBasedSingleton.INSTANCE;

        singleton.setValor(200);
        String filename = System.getProperty("user.dir") + "\\" + "EnumSingleton.txt";
        saveToFile(singleton,filename);

        EnumBasedSingleton singleton2= readFromFile(filename);

        System.out.println(singleton2.getValor());

    }
}
