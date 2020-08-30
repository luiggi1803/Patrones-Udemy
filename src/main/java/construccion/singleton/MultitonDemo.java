package construccion.singleton;

import java.util.HashMap;

enum SubSystem{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer{

    private static int instancesCount =0;

    public Printer() {
        instancesCount++;

        System.out.println("A total of " + instancesCount + " instances created so far.");
    }

    private static HashMap<SubSystem,Printer> instances = new HashMap<>();

    public static Printer get(SubSystem ss){

        if(instances.containsKey(ss))
            return instances.get(ss);

        instances.put(ss,new Printer());

        return instances.get(ss);

    }
}


public class MultitonDemo {

    public static void main(String[] args) {

        Printer primary=Printer.get(SubSystem.PRIMARY);
        Printer aux1=Printer.get(SubSystem.AUXILIARY);
        Printer aux2=Printer.get(SubSystem.FALLBACK);
    }
}

