package construccion.prototype;

class Address {

    private String streetAddress;
    private String city;
    private String country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public Address(Address other) {
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee {
    public String name;
    public Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other) {
        this(other.name, new Address(other.address));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

public class CopyConstructor {

    public static void main(String[] args) {
        Employee luiggi = new Employee("Luiggi", new Address("Chorrillos", "Lima", "Peru"));


        Employee chris = new Employee(luiggi);
        chris.name = "Chris";

        System.out.println(luiggi.toString());
        System.out.println(chris.toString());


    }
}
