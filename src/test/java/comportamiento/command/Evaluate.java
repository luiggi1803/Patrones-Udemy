package comportamiento.command;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

class Command {
    enum Action {
        DEPOSIT, WITHDRAW
    }

    public Action action;
    public int amount;
    public boolean success;

    public Command(Action action, int amount) {
        this.action = action;
        this.amount = amount;
    }
}

class Account {
    public int balance;

    public void process(Command c) {
        switch (c.action) {
            case DEPOSIT:
                balance += c.amount;
                c.success = true;
                break;
            case WITHDRAW:
                c.success = balance >= c.amount;
                if (c.success) balance -= c.amount;
                break;
        }
    }
}

public class Evaluate {
    @Test
    public void test() {
        Account a = new Account();

        Command command = new Command(Command.Action.DEPOSIT, 100);
        a.process(command);

        assertEquals(100, a.balance);
        assertTrue(command.success);

        command = new Command(Command.Action.WITHDRAW, 50);
        a.process(command);

        assertEquals(50, a.balance);
        assertTrue(command.success);

        command = new Command(Command.Action.WITHDRAW, 150);
        a.process(command);

        assertEquals(50, a.balance);
        assertFalse(command.success);
    }
}
