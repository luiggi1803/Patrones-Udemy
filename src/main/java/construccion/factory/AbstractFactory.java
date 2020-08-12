package construccion.factory;

import com.google.common.reflect.Reflection;
import javafx.util.Pair;
import org.reflections.Reflections;
import sun.security.util.Cache;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

interface HotDrink {
    public void consume();
}

class Tea implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This tea is delicious");
    }
}

class Coffee implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This Coffee is delicious");
    }
}


interface HotDrinkFactory {
    public HotDrink prepared(int amount);
}

class TeaFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepared(int amount) {
        System.out.println("Put tea in the bag, boil water, pour " + amount + " ml, add lemon, enjoy!");
        return new Tea();
    }
}

class CoffeeFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepared(int amount) {
        System.out.println("Grid some beans, boil water, pour " + amount + " ml, add cream and sugar, enjoy!");
        return new Coffee();
    }
}

class HotDrinkMachine {
    private List<Pair<String, HotDrinkFactory>> nameFactories = new ArrayList<>();

    public HotDrinkMachine() throws Exception {

        Set<Class<? extends HotDrinkFactory>> types = new Reflections("").getSubTypesOf(HotDrinkFactory.class);

        for (Class<? extends HotDrinkFactory> type : types) {
            nameFactories.add(new Pair<>(
                    type.getSimpleName().replace("Factory", ""),
                    type.getDeclaredConstructor().newInstance()));

        }
    }

    public HotDrink makeDrink() throws Exception {

        System.out.println("Available drinks: ");

        for (int i = 0; i < nameFactories.size(); i++) {
            System.out.println("" + i + ": " + nameFactories.get(i).getKey());
        }
        Scanner sc = new Scanner(System.in);

        while (true) {
            String s;
            Integer i = Integer.parseInt(sc.next());
            int amount;

            if (i != null && i >= 0 && i < nameFactories.size()) {
                System.out.println("Specified amount: ");
                return nameFactories.get(i).getValue().prepared(sc.nextInt());
            }

            System.out.println("Incorrect input, try again.");

        }

    }
}

public class AbstractFactory {
    public static void main(String[] args) throws Exception {
        HotDrinkMachine hotDrinkMachine = new HotDrinkMachine();
        HotDrink hotDrink = hotDrinkMachine.makeDrink();
        hotDrink.consume();
    }
}
