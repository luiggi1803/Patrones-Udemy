package estructural.decorador;

import java.util.function.Supplier;

interface Shape1 {
    String info();
}

class Circle1 implements Shape1 {
    private float radius;

    Circle1() {
    }

    public Circle1(float radius) {
        this.radius = radius;
    }

    void resize(float factor) {
        radius *= factor;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square1 implements Shape1 {
    private float side;

    public Square1() {
    }

    public Square1(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square with side " + side;
    }
}

// we are NOT altering the base class of these objects
// cannot make ColoredSquare, ColoredCircle

class ColoredShape1<T extends Shape1> implements Shape1 {
    private Shape1 shape;
    private String color;

    public ColoredShape1(Supplier<? extends T> ctor, String color) {
        shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape + " has the color " + color;
    }
}

class TransparentShape1<T extends Shape1> implements Shape1 {
    private Shape1 shape;
    private int transparency;

    public TransparentShape1(Supplier<? extends T> ctor, int transparency) {
        shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape + " has " + transparency + "% transparency";
    }
}

public class StaticDecoratorDemo {
    public static void main(String[] args) {
        Circle1 circle = new Circle1(10);
        System.out.println(circle.info());

        ColoredShape1<Square1> blueSquare = new ColoredShape1<>(() -> new Square1(20), "blue");
        System.out.println(blueSquare.info());

        // ugly!
        TransparentShape1<ColoredShape1<Circle1>> myCircle =
                new TransparentShape1<>(() ->
                        new ColoredShape1<>(() -> new Circle1(5), "green"), 50
                );
        System.out.println(myCircle.info());
        // cannot call myCircle.resize()
    }
}
