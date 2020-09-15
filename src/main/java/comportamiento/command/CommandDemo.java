package comportamiento.command;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

class BankAccount {
    private int balance;
    private int overdraftLimit = 0;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposite " + amount + ", balance is now " + balance);
    }

    public boolean withdraw(int amount) {

        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdraw " + amount + ", balance is now " + balance);
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command {
    void call();

    void undo();
}

class BankAccountCommand implements Command {

    private BankAccount bankAccount;
    private boolean succeeded;

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT:
                succeeded = true;
                bankAccount.deposit(amount);
                break;
            case WITHDRAW:
                succeeded = bankAccount.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() {
        if (!succeeded) return;
        switch (action) {
            case DEPOSIT:
                bankAccount.withdraw(amount);
                break;
            case WITHDRAW:
                bankAccount.deposit(amount);
                break;
        }
    }

    public enum Action {
        DEPOSIT, WITHDRAW
    }

    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount bankAccount, Action action, int amount) {
        this.bankAccount = bankAccount;
        this.action = action;
        this.amount = amount;
    }


}

public class CommandDemo {

    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        System.out.println(ba);

        List<BankAccountCommand> bankAccountCommands = Arrays.asList(new BankAccountCommand(ba,
                        BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000));

        for (Command bac : bankAccountCommands) {
            bac.call();
            System.out.println(ba);
        }

        for (Command bac : Lists.reverse(bankAccountCommands)) {
            bac.undo();
            System.out.println(ba);
        }


    }
}
