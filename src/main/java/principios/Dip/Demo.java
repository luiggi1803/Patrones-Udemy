package principios.Dip;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum RelationShip {
    PARENT, CHILD
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}


interface RelationShipBrowser {
    List<Person> findAllChildrenOf(String name);
}


class RelationShips implements RelationShipBrowser { //low-level
    private List<Triplet<Person, RelationShip, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, RelationShip, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, RelationShip.PARENT, child));
        relations.add(new Triplet<>(child, RelationShip.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1().equals(RelationShip.PARENT))
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

class Research { //high-level

//    public Research(RelationShips relationShip) {
//        List<Triplet<Person, RelationShip, Person>> relations = relationShip.getRelations();
//        relations.stream()
//                .filter(x -> x.getValue0().name.equals("Jhon") && x.getValue1().equals(RelationShip.PARENT))
//                .forEach(ch -> System.out.println("Jhon has a child calles: " + ch.getValue2().name));
//
//    }

    public Research(RelationShipBrowser relationShipBrowser){
        List<Person> children = relationShipBrowser.findAllChildrenOf("Jhon");
        children.forEach(x-> System.out.println("Jhon has a child called " + x.name));
    }

}

public class Demo {
    public static void main(String[] args) {
        Person parent = new Person("Jhon");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        RelationShips relationShips = new RelationShips();
        relationShips.addParentAndChild(parent, child1);
        relationShips.addParentAndChild(parent, child2);

        Research research = new Research(relationShips);
    }
}
