package principios.srp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntries(String text) {
        entries.add("" + (++count) + ": " + text);
    }

    public void remove(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence {
    public void saveToFile(Journal journal, String fileName, boolean overwrite) throws FileNotFoundException {
        if (overwrite && new File(fileName).exists()) {

            try (PrintStream out = new PrintStream(fileName)) {
                out.println(journal.toString());
            }
        }
    }
}


public class Demo {

    public static void main(String[] args) throws IOException {

        Journal journal = new Journal();
        journal.addEntries("I eat a good breakfast");
        journal.addEntries("I wacth a tv");
        System.out.println(journal.toString());

        String fileName="D:\\prueba.txt";
        Persistence p = new Persistence();
        p.saveToFile(journal,fileName,true);

        Runtime.getRuntime().exec("notepad.exe " + fileName);

    }

}
