package il.ac.telhai.ds.misc;

import java.util.Objects;

public class Person implements Comparable<Person>{
    private String id ;
    private  String firstName;
    private String lastName;

    public Person(String ID , String Fname , String Lname){
        id = ID;
        firstName = Fname;
        lastName = Lname;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public int compareTo(Person o) {
        int res = id.compareTo(o.id);
        if(res < 0 ){return -1;}
        if(res == 0){return 0;}
        return 1;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person [" +
                "id='" + id + ',' +
                ", firstName='" + firstName + ',' +
                ", lastName='" + lastName + ',' +
                ']';
    }
}
