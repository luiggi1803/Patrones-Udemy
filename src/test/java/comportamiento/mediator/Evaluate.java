package comportamiento.mediator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

class Participant {
    private Mediator mediator;
    public int value;

    public Participant(Mediator mediator) {
        this.mediator = mediator;
        mediator.addListener(this);
    }

    public void say(int n) {
        mediator.broadcast(this, n);
    }

    public void notify(Object sender, int n) {
        if (sender != this)
            value += n;
    }
}

class Mediator {
    private List<Participant> listeners = new ArrayList<>();

    public void broadcast(Object sender, int n) {
        for (Participant p : listeners)
            p.notify(sender, n);
    }

    public void addListener(Participant p) {
        listeners.add(p);
    }
}

public class Evaluate {
    @Test
    public void test() {
        Mediator mediator = new Mediator();
        Participant p1 = new Participant(mediator);
        Participant p2 = new Participant(mediator);

        assertEquals(0, p1.value);
        assertEquals(0, p2.value);

        p1.say(2);

        assertEquals(0, p1.value);
        assertEquals(2, p2.value);

        p2.say(4);

        assertEquals(4, p1.value);
        assertEquals(2, p2.value);

    }
}
