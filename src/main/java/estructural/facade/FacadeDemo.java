package estructural.facade;

import java.util.ArrayList;
import java.util.List;

class Buffer {

    private char[] characters;
    private int lineWidth;

    public Buffer(int lineHeight, int lineWidth) {
        this.lineWidth = lineWidth;
        this.characters = new char[lineHeight];
    }

    public char charAt(int x, int y) {
        return characters[y * lineWidth + x];
    }
}

class ViewPort {

    private final Buffer buffer;
    private final int width;
    private final int height;
    private final int offsetX;
    private final int offsetY;

    public ViewPort(Buffer buffer, int width, int height, int offsetX, int offsetY) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public char charAt(int x, int y) {
        return buffer.charAt(x + offsetX, y + offsetY);
    }
}

class Console {
    private List<ViewPort> viewPorts = new ArrayList<>();
    int width;
    int height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addViewport(ViewPort viewPort) {
        viewPorts.add(viewPort);
    }

    public static Console newConsole(int width, int height) {
        Buffer buffer = new Buffer(width, height);
        ViewPort viewPort = new ViewPort(buffer, width, height, 0, 0);
        Console console = new Console(width, height);
        console.addViewport(viewPort);
        return console;

    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < height; x++) {
                for (ViewPort viewPort : viewPorts) {
                    System.out.println(viewPort.charAt(x, y));
                }
                System.out.println();
            }
        }
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        Console console = Console.newConsole(20, 30);
        console.render();
    }
}
