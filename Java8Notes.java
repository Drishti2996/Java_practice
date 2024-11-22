public class Java8Notes {
    
    /**
     * Java 8 Features

Oracle released a new version of Java as Java 8 in March 18, 2014. It was a revolutionary release of the Java for software development platform. It includes various upgrades to the Java programming, JVM, Tools and libraries.
Java 8 Programming Language Enhancements

Java 8 provides following features for Java Programming:

    Lambda expressions,
    Method references,
    Functional interfaces,
    Stream API,
    Default methods,
    Base64 Encode Decode,
    Static methods in interface,
    Optional class,
    Collectors class,
    ForEach() method,
    Nashorn JavaScript Engine,
    Parallel Array Sorting,
    Type and Repating Annotations,
    IO Enhancements,
    Concurrency Enhancements,
    JDBC Enhancements etc.


     * 
     * 
     * 
     * 
     */

     /*
      * 1. Lambda Expressions

Lambda expression helps us to write our code in functional style. 
It provides a clear and concise way to implement SAM interface (Single Abstract Method) 
by using an expression

The basic syntax of a lambda expression is:

    (argument-list) -> {body}  

    argument-list: It can be empty or non-empty as well. It represents the parameters used by the expression.
    arrow-token (->): It links the arguments to the body of the expression.
    body: It contains expressions and statements for the lambda expression.




    2. Functional Interface

An Interface that contains only one abstract method is known as functional interface.
 It can have any number of default and static methods. It can also declare methods of object class.

Functional interfaces are also known as Single Abstract Method Interfaces (SAM Interfaces).

Functional Interfaces

    Definition: An interface with exactly one abstract method, used for lambda expressions.
    Common Examples:
        Runnable -     Definition: A functional interface representing a task that can be executed by a thread. It does not return a result or take any arguments.
    Method: void run()
Example:
Runnable task = () -> System.out.println("Task is running");
new Thread(task).start();


        Callable -     Definition: A functional interface similar to Runnable but can return a result and throw exceptions.
    Method: V call() throws Exception

Example:

Callable<String> callableTask = () -> "Callable task completed";
ExecutorService executor = Executors.newSingleThreadExecutor();
Future<String> result = executor.submit(callableTask);
System.out.println(result.get()); // Callable task completed
executor.shutdown();


        Predicate<T>: Returns a boolean. -     Definition: A functional interface that takes an input of type T and returns a boolean value.
    Method: boolean test(T t)
Example:
Predicate<Integer> isEven = num -> num % 2 == 0;

        Consumer<T>: Consumes an input but doesn’t return anything. - Example:
Consumer<String> printConsumer = System.out::println;
printConsumer.accept("Hello, Consumer!"); // Hello, Consumer!


        Supplier<T>: Supplies a result without input.     Definition: A functional interface that supplies a value of type T without taking any input.
    Method: T get()
Example:
Supplier<Double> randomSupplier = Math::random;
System.out.println(randomSupplier.get()); // Prints a random number
        
Function<T, R>: Takes input of type T and returns R.
    A functional interface that takes an input of type T and returns a result of type R.
    Method: R apply(T t)
Example:
Function<Integer, String> intToString = num -> "Number: " + num;
System.out.println(intToString.apply(5)); // Number: 5




3. Optional

Java introduced a new class Optional in Java 8. It is a public final class which is used to deal 
with NullPointerException in Java application. We must import java.util package to use this class. 
It provides methods to check the presence of value for particular variable
Key Methods:

    isPresent(): Checks if a value is present.
    orElse(): Provides a default value if Optional is empty.
    ifPresent(): Executes a block of code if a value is present.
   
    Optional<String> name = Optional.ofNullable("Alice");
name.ifPresent(System.out::println); // Alice
System.out.println(name.orElse("Default")); // Alice




4. New Map Methods

    forEach: Iterate over key-value pairs.
    computeIfAbsent / computeIfPresent: Compute a value for a key.
    merge: Merge a value for a key.

Example:

Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.computeIfAbsent("B", k -> 2);
System.out.println(map); // {A=1, B=2}



 5. Date/Time API

Java has introduced a new Date and Time API since Java 8. 
The java.time package contains Java 8 Date and Time classes

API Specification

    java.time: Core classes for dates, times, combined date and time, instants, durations, periods, and clocks using the ISO-8601 system.
    java.time.chrono: Supports non-ISO calendar systems with predefined and custom chronologies.
    java.time.format: For formatting and parsing date-time objects.
    java.time.temporal: Advanced features for date-time manipulation, aimed at library developers.
    java.time.zone: Handles time zones, offsets, and rules.



   6.  Stream API

    Definition: A new way to process collections of data in a functional style.
    Key Features:
        Intermediate Operations: filter, map, sorted, distinct, flatMap.
        Terminal Operations: collect, forEach, reduce, count, min, max.


    7. Default and Static Methods in Interfaces

    Default Methods: Allow interfaces to have method implementations.
    Static Methods: Provide utility methods within interfaces.

Example:

interface MyInterface {
    default void defaultMethod() {
        System.out.println("Default Method");
    }
    static void staticMethod() {
        System.out.println("Static Method");
    }
}

    8. Collectors API

    Definition: Used in conjunction with the Stream API to collect results into a desired data structure.
    Common Collectors:
        groupingBy: Groups elements by a classifier.
        counting: Counts the number of elements.
        joining: Joins elements into a string.
        summingInt, averagingInt: Aggregates values.


    9. Method References

    Definition: A shorthand for lambda expressions referring to a method by name.
    Types:
        Static methods: ClassName::staticMethod
        Instance methods: instance::method
        Constructors: ClassName::new

Example:

List<String> names = List.of("Alice", "Bob");
names.forEach(System.out::println);
Method references are a shorthand notation for writing lambda expressions. Types include:

    Static Methods: ClassName::staticMethod

Function<String, Integer> toInteger = Integer::parseInt;
System.out.println(toInteger.apply("123")); // 123

Instance Methods of a Particular Object: instance::method

Consumer<String> print = System.out::println;
print.accept("Hello"); // Hello

Instance Methods of an Arbitrary Object: ClassName::instanceMethod

Function<String, Integer> stringLength = String::length;
System.out.println(stringLength.apply("Hello")); // 5

Constructors: ClassName::new

    Supplier<List<String>> listSupplier = ArrayList::new;
    List<String> list = listSupplier.get();



    10. Parallel Streams

    Definition: Allows parallel execution of stream operations to improve performance.
    Usage:

    List<Integer> numbers = List.of(1, 2, 3, 4, 5);
    numbers.parallelStream()
        .forEach(num -> System.out.println(Thread.currentThread().getName() + " : " + num));




        11. Base64 Encoding and Decoding

    New API: java.util.Base64
    Usage:

    String encoded = Base64.getEncoder().encodeToString("Hello".getBytes());
    System.out.println(encoded); // Encoded string
    System.out.println(new String(Base64.getDecoder().decode(encoded))); // Hello



    12. CompletableFuture

    Definition: Asynchronous programming using CompletableFuture.
    Key Methods:
        supplyAsync: Runs a task asynchronously.
        thenApply: Transforms the result.
        thenCombine: Combines results of two futures.

Example:

CompletableFuture.supplyAsync(() -> "Hello")
    .thenApply(greeting -> greeting + " World")
    .thenAccept(System.out::println); // Hello World

    A CompletableFuture allows asynchronous computation with chaining and combining tasks.

Key Methods:

    supplyAsync: Run tasks asynchronously.
    thenApply: Transform the result.
    thenAccept: Consume the result.
    thenCombine: Combine two tasks' results.
    exceptionally: Handle exceptions.

Example:

CompletableFuture.supplyAsync(() -> "Hello")
    .thenApply(greeting -> greeting + " World")
    .thenAccept(System.out::println); // Hello World

// Combining two CompletableFutures
CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5);
CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 10);

future1.thenCombine(future2, Integer::sum)
    .thenAccept(System.out::println); // 15



      */
}
