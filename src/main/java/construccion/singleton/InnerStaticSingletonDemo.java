package construccion.singleton;

class InnerStaticSingleton{
    //Evita Hacer sincronizacion en hilos
    public InnerStaticSingleton() {
    }

    private static class Impl {
        private static final InnerStaticSingleton INSTANCE= new InnerStaticSingleton();
    }

    public InnerStaticSingleton getInstance(){
        return Impl.INSTANCE;
    }
}

public class InnerStaticSingletonDemo {
}
