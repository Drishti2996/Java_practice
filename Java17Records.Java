/**
 * Records are a special kind of class in Java designed to be immutable data carriers. They significantly reduce boilerplate code by automatically generating common methods like toString(), equals(), hashCode(), and getter methods.
Why Use Records?

    Less Boilerplate Code:
        Traditional Java classes for data representation require manual creation of constructors, getters, equals(), hashCode(), and toString(). Records automate this.

    Immutability:
        Records are implicitly final and their fields are immutable by default.

    Improved Readability:
        Cleaner, more concise code improves maintainability.

Defining a Record

Here’s how you define a record in Java:

public record Person(String name, int age) {}

This automatically generates:

    Constructor: public Person(String name, int age)
    Getters: name() and age()
    equals() and hashCode()
    toString()

Usage Example
Record Definition:

public record Person(String name, int age) {}

Using the Record:

public class RecordExample {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30);

        // Access fields
        System.out.println(person.name()); // Alice
        System.out.println(person.age());  // 30

        // toString() automatically generated
        System.out.println(person); // Person[name=Alice, age=30]

        // Equals and hashCode
        Person anotherPerson = new Person("Alice", 30);
        System.out.println(person.equals(anotherPerson)); // true
    }
}

Features of Records

    Automatic Implementation of Common Methods:
        Constructor, equals(), hashCode(), and toString() are automatically generated.

    Immutable Fields:
        Fields are private and final by default.
        No setters are generated.

    Compact Code:
        No need for boilerplate code in simple data-carrying classes.

    Use with Collections:
        Records work seamlessly with Set and Map due to their proper equals() and hashCode() implementations.

    Custom Implementations Allowed:
        You can override methods like toString(), equals(), and hashCode() if necessary.
        You can define additional methods in the record.

Customizing a Record

You can customize the constructor or add methods.
Compact Constructor:

public record Person(String name, int age) {
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}

Additional Methods:

public record Person(String name, int age) {
    public String greet() {
        return "Hello, " + name + "!";
    }
}

Limitations of Records

    Cannot Extend Other Classes:
        Records are implicitly final and cannot extend other classes.
        They can implement interfaces.

    Not for All Use Cases:
        Records are ideal for data transfer objects (DTOs) or simple immutable data containers, not for classes requiring complex behavior.

    Immutable by Design:
        If mutability is required, you need to use traditional classes.

**/