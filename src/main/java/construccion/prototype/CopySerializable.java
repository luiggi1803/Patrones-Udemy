package construccion.prototype;


import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

class Foo implements Serializable {

    public int stuff;
    public String whatever;

    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}


public class CopySerializable {
    
    public static void main(String[] args) {

        Foo foo = new Foo(45, "life");
        Foo foo2 = SerializationUtils.roundtrip(foo);
        foo2.whatever="asd";
        System.out.println(foo.toString());
        System.out.println(foo2.toString());


    }
}