package comportamiento.chainOfResponsability;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

abstract class Creature {
    protected Game game;
    protected int baseAttack, baseDefense;

    public Creature(Game game, int baseAttack, int baseDefense) {
        this.game = game;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    public abstract int getAttack();

    public abstract int getDefense();

    public abstract void query(Object source, StatQuery sq);
}

class Goblin extends Creature {
    protected Goblin(Game game, int baseAttack, int baseDefense) {
        super(game, baseAttack, baseDefense);
    }

    public Goblin(Game game) {
        super(game, 1, 1);
    }

    @Override
    public int getAttack() {
        StatQuery q = new StatQuery(Statistic.ATTACK);
        for (Creature c : game.creatures)
            c.query(this, q);
        return q.result;
    }

    @Override
    public int getDefense() {
        StatQuery q = new StatQuery(Statistic.DEFENSE);
        for (Creature c : game.creatures)
            c.query(this, q);
        return q.result;
    }

    @Override
    public void query(Object source, StatQuery sq) {
        if (source == this) {
            switch (sq.statistic) {
                case ATTACK:
                    sq.result += baseAttack;
                    break;
                case DEFENSE:
                    sq.result += baseDefense;
                    break;
            }
        } else if (sq.statistic == Statistic.DEFENSE) {
            sq.result++;
        }
    }
}

class GoblinKing extends Goblin {
    public GoblinKing(Game game) {
        super(game, 3, 3);
    }

    @Override
    public void query(Object source, StatQuery sq) {
        if (source != this && sq.statistic == Statistic.ATTACK) {
            sq.result++; // every goblin gets +1 attack
        } else super.query(source, sq);
    }
}

enum Statistic {
    ATTACK, DEFENSE
}

class StatQuery {
    public Statistic statistic;
    public int result;

    public StatQuery(Statistic statistic) {
        this.statistic = statistic;
    }
}

class Game {
    public List<Creature> creatures = new ArrayList<>();
}

public class Evaluate {
    @Test
    public void manyGoblinsTest() {
        Game game = new Game();
        Goblin goblin = new Goblin(game);
        game.creatures.add(goblin);

        assertEquals(1, goblin.getAttack());
        assertEquals(1, goblin.getDefense());

        Goblin goblin2 = new Goblin(game);
        game.creatures.add(goblin2);

        assertEquals(1, goblin.getAttack());
        assertEquals(2, goblin.getDefense());

        GoblinKing goblin3 = new GoblinKing(game);
        game.creatures.add(goblin3);

        assertEquals(2, goblin.getAttack());
        assertEquals(3, goblin.getDefense());
    }
}
