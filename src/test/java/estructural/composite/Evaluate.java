package estructural.composite;

import java.util.*;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

interface ValueContainer extends Iterable<Integer> {
}

class SingleValue implements ValueContainer {
    public int value;

    public SingleValue(int value) {
        this.value = value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Collections.singleton(value).iterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {
}


class MyList extends ArrayList<ValueContainer> {
    public MyList(Collection<? extends ValueContainer> c) {
        super(c);
    }

    public int sum() {
        int result = 0;
        for (ValueContainer c : this) {
            for (int i : c) {
                result += i;
            }
        }
        return result;
    }
}

public class Evaluate {
    @Test
    public void test() {
        SingleValue singleValue = new SingleValue(11);
        ManyValues otherValues = new ManyValues();
        otherValues.add(22);
        otherValues.add(33);
        assertEquals(66,
                new MyList(Arrays.asList(singleValue, otherValues)).sum());
    }
}
