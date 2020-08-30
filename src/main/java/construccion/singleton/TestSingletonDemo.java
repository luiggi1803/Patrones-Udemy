package construccion.singleton;

import com.google.common.collect.Iterables;

import java.io.File;
import java.nio.file.Files;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

class SingletonDataBase {

    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instancesCount = 0;

    public static int getCount() {
        return instancesCount;
    }

    ;

    private SingletonDataBase() {
        instancesCount++;
        System.out.println("Initialization Database");

        try {

            String filename = System.getProperty("user.dir") + "\\" + "capitals.txt";
            File file = new File(filename);
            List<String> lines = Files.readAllLines(file.toPath());

            Iterables.partition(lines, 2).forEach(kv -> {
                capitals.put(kv.get(0).trim(), Integer.parseInt(kv.get(1)));
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static final SingletonDataBase INSTANCE = new SingletonDataBase();

    public static SingletonDataBase getInstance() {
        return INSTANCE;
    }

    public int getPoputation(String name) {
        return capitals.get(name);
    }

}

class SingletonRecordFinder {

    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names)
            result += SingletonDataBase.getInstance().getPoputation(name);

        return result;
    }
}

public class TestSingletonDemo {
}
