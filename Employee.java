
// A Stream in Java is an abstraction that represents a sequence 
//of elements and supports various operations to process data in 
//a functional programming style. It is part of the java.util.stream package introduced in Java 8.
//Streams do not modify the source collection but produce a new stream or result.


// The Collectors class provides implementations of the Collector interface, 
// which is used to perform mutable reductions on the elements of a stream
// (e.g., collecting elements into a List, Set, Map, etc.). 
//It is part of the java.util.stream package.

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private String department;
    private int salary;

    public Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + "', salary=" + salary + '}';
    }

    public static void main(String a[]){ 
        List<Employee> employees = List.of(
            new Employee("Alice", "HR", 3000),
            new Employee("Bob", "IT", 5000),
            new Employee("Charlie", "HR", 4000),
            new Employee("David", "IT", 6000)
        );

        //Collectors.groupingBy
//Used to group elements in a stream by a classifier function.
        
        Map<String, List<Employee>> employeesByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        
        employeesByDept.forEach((dept, empList) -> 
            System.out.println(dept + " : " + empList));
            System.out.println("");

// Get the Sum of a Particular Department
// Combine groupingBy with Collectors.summingInt.

        Map<String, Integer> totalSalariesByDept = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, 
        Collectors.summingInt(Employee::getSalary)));

        totalSalariesByDept.forEach((dept, totalSalary) -> 
        System.out.println(dept + " : " + totalSalary));
        System.out.println("");

        // Counting Frequency
      // Use Collectors.groupingBy with Collectors.counting to count occurrences
      Map<String, Long> employeeCountByDept = employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment, 
          Collectors.counting()));
  
    employeeCountByDept.forEach((dept, count) -> 
      System.out.println(dept + " : " + count));
  

      // Counting Average
    // Use Collectors.averagingInt to calculate averages.
    Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment, 
        Collectors.averagingInt(Employee::getSalary)));

avgSalaryByDept.forEach((dept, avgSalary) -> 
    System.out.println(dept + " : " + avgSalary));

    //Find the Maximum Number of People in a Particular Group
   // Use Collectors.groupingBy and sort based on group size.

   String deptWithMostEmployees = employees.stream()
   .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
   .entrySet()
   .stream()
   .max(Map.Entry.comparingByValue())
   .map(Map.Entry::getKey)
   .orElse("No Department");

System.out.println("Department with most employees: " + deptWithMostEmployees);

// Find the First Element
//Use Stream.findFirst to get the first element in a stream.

Employee firstEmployee = employees.stream()
    .findFirst()
    .orElse(null);

System.out.println("First Employee: " + firstEmployee);

//Skip
//Use Stream.skip to skip elements in a stream.
//Example: Skip the first 2 employees and print the rest
employees.stream()
    .skip(2)
    .forEach(System.out::println);

   // Get Max
   // Use Stream.max to find the maximum element based on a comparator.
   // Example: Get the highest salary
   Employee highestPaidEmployee = employees.stream()
    .max(Comparator.comparingInt(Employee::getSalary))
    .orElse(null);

System.out.println("Highest Paid Employee: " + highestPaidEmployee);

// Count
// Use Stream.count to count the number of elements in a stream.
// Example: Count the number of employees

long totalEmployees = employees.stream()
    .count();

System.out.println("Total Employees: " + totalEmployees);


// Traverse a Map Using Streams
//Use Map.entrySet() and Stream to traverse a map.
//Example: Print key-value pairs in a map
Map<String, Integer> deptSalaryMap = Map.of("HR", 7000, "IT", 11000);

deptSalaryMap.entrySet().stream()
    .forEach(entry -> 
        System.out.println("Department: " + entry.getKey() + ", Total Salary: " + entry.getValue()));


      //  1. Streams: Intermediate Operations
      //  Filter
           // Used to filter elements based on a condition.
        
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println(evenNumbers); // [2, 4, 6]


        //Map
    //Transforms each element into another form.
    List<String> names = List.of("Alice", "Bob", "Charlie");
List<Integer> nameLengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());
System.out.println(nameLengths); // [5, 3, 7]


//FlatMap
//Flattens nested structures (like lists of lists).

List<List<String>> nestedLists = List.of(
    List.of("A", "B"),
    List.of("C", "D"),
    List.of("E", "F")
);
List<String> flatList = nestedLists.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
System.out.println(flatList); // [A, B, C, D, E, F]

/**Practice Exercise: Intermediate Operations

    Filter a list of numbers to retain only those greater than 10.
    Transform a list of strings into their uppercase equivalents.
    Flatten a list of lists of integers into a single list of integers. */

    //Edge Cases
   // Empty Streams: Handle empty streams using Optional or default values:

    List<Integer> emptyList = List.of();
    Optional<Integer> max = emptyList.stream().max(Integer::compareTo);
    System.out.println(max.orElse(0)); // Default to 0
    
    
    //Null Values: Filter out nulls explicitly:
List<String> names1 = Arrays.asList("Alice", null, "Bob");
List<String> nonNullNames = names1.stream()
    .filter(Objects::nonNull)
    .collect(Collectors.toList());
System.out.println(nonNullNames); // [Alice, Bob]

            }
}
