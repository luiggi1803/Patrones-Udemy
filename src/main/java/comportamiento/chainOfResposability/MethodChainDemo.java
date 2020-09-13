package comportamiento.chainOfResposability;

class Creature {
    public String name;
    public int attack;
    public int defense;

    public Creature(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}

class CreatureModifier {
    protected Creature creature;
    protected CreatureModifier next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    public void add(CreatureModifier cm) {
        if (next != null) {
            next.add(cm);
        } else {
            next = cm;
        }
    }

    public void handle() {
        if (next != null) next.handle();
    }
}

class DoubleAttackModifier extends CreatureModifier {

    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + creature.name + "´s attack");
        creature.attack *= 2;
        super.handle();
    }
}

class IncreaseDefenseModifier extends CreatureModifier {

    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Increasing " + creature.name + "´s defense");
        creature.defense += 3;
        super.handle();
    }
}

class NoBonusesModifier extends CreatureModifier {

    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("No Bonuses for you!");
    }

}

public class MethodChainDemo {

    public static void main(String[] args) {
        Creature globin = new Creature("Globin", 2, 2);
        System.out.println(globin);

        CreatureModifier root = new CreatureModifier(globin);

        root.add(new NoBonusesModifier(globin));

        System.out.println("Let's double globin´s attack...");
        root.add(new DoubleAttackModifier(globin));

        System.out.println("Let's increase globin´s defense...");
        root.add(new IncreaseDefenseModifier(globin));
        root.handle();
        System.out.println(globin);

    }
}
