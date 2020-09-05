package construccion.Singleton;

import com.google.common.collect.Iterables;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

import static org.junit.Assert.assertEquals;

class SingletonDataBase implements Database {

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

    public int getPopulation(String name) {
        return capitals.get(name);
    }

}

interface Database {
    int getPopulation(String name);
}

class ConfigurableRecordFinder {

    private Database database;

    public ConfigurableRecordFinder(Database db) {
        this.database = db;
    }

    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names)
            result += database.getPopulation(name);

        return result;
    }
}

class DummyDatabase implements Database {

    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}

public class TestSingleton {

    @Test //Not a unit Test
    public void singletonTotalPopulationTest() {
        SingletonDataBase db= SingletonDataBase.getInstance();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        int tp = rf.getTotalPopulation(Arrays.asList("Tokyo","New York"));
        assertEquals(33200000 + 17800000, tp);
    }

    @Test
    public void dependedPopulationTest() {
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        int tp = rf.getTotalPopulation(Arrays.asList("beta", "gamma"));
        assertEquals(5,tp);

    }

}
