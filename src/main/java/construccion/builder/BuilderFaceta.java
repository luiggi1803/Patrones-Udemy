package construccion.builder;

class Person2 {
    //address
    public String streetAddress;
    public String postCode;
    public String city;

    //employment
    public String company;
    public String position;
    public int anualInCome;

    @Override
    public String toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", anualInCome=" + anualInCome +
                '}';
    }
}

//builderFacade
class PersonBuilder2 {
    protected Person2 person = new Person2();

    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }

    public Person2 build() {
        return person;
    }

}

class PersonAddressBuilder extends PersonBuilder2 {

    public PersonAddressBuilder(Person2 person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress) {
        this.person.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postCode) {
        this.person.postCode = postCode;
        return this;
    }

    public PersonAddressBuilder in(String city) {
        this.person.city = city;
        return this;
    }

}

class PersonJobBuilder extends PersonBuilder2 {

    public PersonJobBuilder(Person2 person) {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName) {
        this.person.company = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position) {
        this.person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int anualInCome) {
        this.person.anualInCome = anualInCome;
        return this;
    }
}


public class BuilderFaceta {

    public static void main(String[] args) {
        PersonBuilder2 personBuilder = new PersonBuilder2();
        personBuilder.lives()
                .at("Chorrillos").withPostcode("Lima01").in("Peru")
                .works().asA("Developer").at("TCS").earning(3000);

        System.out.println(personBuilder.build());
    }

}
