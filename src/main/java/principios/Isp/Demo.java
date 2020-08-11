package principios.Isp;

class Document {

}

interface Machine {
    public void print(Document document);

    public void fax(Document document) throws Exception;

    public void scan(Document document);

}

class MachineMultifunctional implements Machine{

    @Override
    public void print(Document document) {
        //
    }

    @Override
    public void fax(Document document) {
        //
    }

    @Override
    public void scan(Document document) {
        //
    }
}


class OldMachine implements Machine{

    @Override
    public void print(Document document) {

    }

    @Override
    public void fax(Document document) throws Exception {
        throw new Exception("No implements");
    }

    @Override
    public void scan(Document document) {

    }
}

//IDEAL
interface Printer{
    public void print(Document document);
}

interface Scanner{
    public void scan (Document document);
}

//YAGNI = You Ain't Going to need it

class JustAPrinter implements Printer{

    @Override
    public void print(Document document) {

    }
}

class Photocopier implements Printer,Scanner{

    @Override
    public void print(Document document) {

    }

    @Override
    public void scan(Document document) {

    }
}

//Otra forma
interface MultiFunctionDevice extends Scanner,Printer{}

class MultiFunctionMachine implements MultiFunctionDevice{

    Printer printer;
    Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document document) {
        printer.print(document);
    }

    @Override
    public void scan(Document document) {
        scanner.scan(document);
    }
}

public class Demo {
    public static void main(String[] args) {

    }
}
