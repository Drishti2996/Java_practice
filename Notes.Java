///idea.sh
/// Most Important Annotations in Spring Boot
@SpringBootApplication:
// This is the main annotation used to mark the entry point of a Spring Boot application. It combines three important annotations:
@EnableAutoConfiguration
// Enables Spring Boot's auto-configuration feature.
//@ComponentScan: Tells Spring to scan for other components, configurations, and services in the specified package.
///@Configuration: Indicates that the class provides Spring configuration.
//Example:
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}


@RestController:
//A specialized version of @Controller that is used to create RESTful web services. 
//It is typically used to return data (in JSON or XML format) from the server.
//This annotation combines @Controller and @ResponseBody to make it easier to write REST APIs.
//Example:
@RestController
public class MyController {
    @GetMapping("/greet")
    public String greet() {
        return "Hello, Spring Boot!";
    }
}


//@RequestMapping / @GetMapping, @PostMapping, etc.:
//Used to map HTTP requests to handler methods in a controller. 
//@RequestMapping can be used with HTTP methods (GET, POST, PUT, DELETE, etc.), 
//but specific shortcuts like @GetMapping and @PostMapping are often used for cleaner and more specific code.
//Example:
@RequestMapping("/hello")
public String hello() {
    return "Hello, World!";
}
//Or using the shortcut:

@GetMapping("/hello")
public String hello() {
    return "Hello, World!";
}
//@Component / @Service / @Repository / @Controller:
//These are specialized versions of the @Component annotation, 
//used to define Spring beans with specific roles:
//@Component: Generic component (bean).
//@Service: Typically used for service-layer beans.
//@Repository: Typically used for data access layer beans.
//@Controller: Typically used for web controllers in MVC.
//@Value:Used to inject values into fields from property files, such as application.properties or application.yml.
//Example:
@Value("${my.property}")
private String myProperty;

//@Configuration:
//Marks a class as a source of bean definitions. 
//Classes annotated with @Configuration can define beans with @Bean methods.
//Example:

@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}

@EnableAutoConfiguration:
//As discussed earlier, this annotation is used to enable Spring Boot's automatic configuration feature,
// which attempts to configure your application based on the libraries you have on the classpath.
@Bean:
//Used within a @Configuration class to define a bean in the Spring application context.
//Example:
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}

@SpringBootTest:
//Used for integration tests in Spring Boot applications. 
//It provides a way to test the application in an actual Spring context.
Example:
@SpringBootTest
public class MyApplicationTests {
    @Test
    public void contextLoads() {
        // test logic
    }
}
//Actuator – Spring Boot Actuator provides health check, metrics and monitors the endpoints of 
//the application. It also simplifies the troubleshooting management.

/*
Features of springboot
Auto-configuration – Spring Boot automatically configures dependencies by using @EnableAutoconfiguration annotation and reduces boilerplate code.
Spring Boot Starter POM – These Starter POMs are pre-configured dependencies for functions like database, security, maven configuration etc.
Spring Boot CLI (Command Line Interface) – This command line tool is generally for managing dependencies, creating projects and running the applications.
Actuator – Spring Boot Actuator provides health check, metrics and monitors the endpoints of the application. It also simplifies the troubleshooting management.
Embedded Servers – Spring Boot contains embedded servers like Tomcat and Jetty for quick application run. No need of external servers.
How can you define the scope of a Spring bean?
Use the @Scope annotation:
singleton: A single instance per Spring container.
prototype: A new instance each time it's requested.
request, session, application: For web applications, based on HTTP scopes.
Write a code example demonstrating Constructor Injection using Spring.
@Component
public class Engine {}


@Component
public class Car {
	private final Engine engine;


	@Autowired
	public Car(Engine engine) {
    	this.engine = engine;
	}
}

*/


/*
 * The @Configuration annotation in Spring is a key part of the framework's configuration mechanism. It is used to define a class that will serve as a source of bean definitions, which are objects managed by the Spring container. Here's a detailed breakdown:
Explanation:

    Marks a Configuration Class:
        When a class is annotated with @Configuration, it indicates that the class contains one or more methods annotated with @Bean.
        These methods are used to define and instantiate Spring beans, which are objects that Spring manages and wires together.

    Java-based Configuration:
        It allows for Java-based configuration of the Spring application context, as opposed to XML-based configuration.
        This approach is more type-safe, flexible, and easier to maintain.

    Integration with Dependency Injection:
        The Spring container processes @Configuration classes to instantiate and manage dependencies (beans) defined within them.

    Use with @Bean Methods:
        Methods within the @Configuration class that are annotated with @Bean define the logic for creating and configuring beans.
        The return value of the @Bean method is registered as a Spring bean.

    Enhances Classes with CGLIB Proxies:
        Behind the scenes, Spring enhances @Configuration classes using CGLIB proxies to ensure that methods are not invoked multiple times, preventing the creation of duplicate beans.

Example:

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }

    @Bean
    public MyRepository myRepository() {
        return new MyRepositoryImpl();
    }
}

Key Points in the Example:

    @Configuration:
        Indicates that AppConfig is a configuration class.
        Spring will scan this class for @Bean methods to create and manage beans.

    @Bean:
        Marks myService and myRepository methods as bean definitions.
        Spring will call these methods, register their return values as beans in the application context, and manage their lifecycle.

    Dependency Injection:
        These beans can then be injected into other components using @Autowired, constructor injection, or setter injection.

Benefits of @Configuration:

    Centralized Configuration: Keeps bean definitions in one place.
    Type-Safety: Java-based configuration eliminates the risk of typos in XML.
    Flexibility: Allows dynamic configuration based on runtime conditions.


 * 
 */
/*
 * Inversion of Control (IoC) is a design principle where the control of objects and their dependencies is transferred 
 * from the application code to a framework or container. It is a core concept in frameworks 
 * like Spring, enabling loose coupling and easier testing.
 * 
 * 
 * Types of Dependency Injection (DI):

Dependency Injection is a key implementation of IoC. The three main types are:

    Constructor Injection: Dependencies are passed via the constructor.

@Component
public class Car {
    private Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}

Setter Injection: Dependencies are passed via setter methods.

@Component
public class Car {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}

Field Injection: Dependencies are directly injected into fields.

    @Component
    public class Car {
        @Autowired
        private Engine engine;
    }

Spring Framework: Uses IoC containers to manage beans and their dependencies.

    @Configuration and @Bean for Java-based configuration.
    @Component, @Controller, @Service, and @Repository for component scanning.

Example: IoC in Action

// Engine Class (Dependency)
@Component
public class Engine {
    public void start() {
        System.out.println("Engine started!");
    }
}

// Car Class (Dependent)
@Component
public class Car {
    private Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is driving!");
    }
}

// Application Configuration
@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {}

// Main Application
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = context.getBean(Car.class);
        car.drive();
    }
}

Output:

Engine started!
Car is driving!

Inversion of Control (IoC) is a design principle where the control of object creation 
and dependency management is inverted from the application code to a framework or container. 
This means the framework manages object creation, initialization, and the injection of dependencies, 
allowing for loose coupling between components.

IoC improves code quality in several ways:

    Loose Coupling: Components are not tightly bound to specific implementations, making the code more modular and flexible.
    Reusability: Decoupled components can be reused across different projects.
    Testability: Dependencies can be easily mocked or replaced for testing.
    Maintainability: Changes to one part of the system do not ripple through the entire codebase.


    What are the differences between IoC and Dependency Injection (DI)?

    IoC: A broader principle of delegating control to a framework or container. It focuses on decoupling components and managing the flow of control.
    DI: A specific design pattern and an implementation mechanism for IoC. It provides dependencies to objects instead of having the objects create them.

In short, DI is a way to implement IoC.

What is the difference between ApplicationContext and BeanFactory?
Aspect	ApplicationContext	BeanFactory
Scope	Advanced container	Basic container
Eager Initialization	Loads all beans at startup	Loads beans lazily
Features	Supports event propagation, AOP, etc.	Limited to basic dependency injection
Usage	Preferred for large-scale applications	Suitable for lightweight applications


Dependency Injection is a design pattern in which an object receives its dependencies from an
 external source rather than creating them itself. It promotes loose coupling between classes, 
 making the code more flexible and easier to test.


 In Spring, the IoC container manages DI automatically. 
 Beans and their dependencies are defined in configuration files or annotations.

    Annotations for DI:
        @Autowired: Automatically injects dependencies.
        @Qualifier: Specifies which bean to inject when multiple beans of the same type exist.
        @Value: Injects values from properties files.


Constructor vs. Setter Injection:

    Constructor injection is preferred for mandatory dependencies as it ensures immutability.
    Setter injection is more suitable for optional dependencies.

Circular Dependency:
When two or more beans depend on each other, leading to an infinite loop. 
Spring handles this using proxies or by refactoring the design to break the cycle.

Circular Dependency:
When two or more beans depend on each other, leading to an infinite loop. Spring handles this using proxies or by refactoring the design to break the cycle.

Example:

@Component
public class A {
    @Autowired
    private B b;
}

@Component
public class B {
    @Autowired
    private A a;
}

Solution:

    Use @Lazy to delay initialization.
    Redesign to eliminate circular references.

Custom Qualifiers:
When multiple beans of the same type exist, use custom qualifiers to specify which bean to inject.

Example:

@Qualifier("electricEngine")
@Component
public class ElectricEngine implements Engine {}



Custom Qualifiers

When there are multiple beans of the same type, Spring needs help determining which bean to inject. The @Qualifier annotation explicitly specifies which bean to use.
Example

@Component
@Qualifier("electricEngine")
public class ElectricEngine implements Engine {}

@Component
@Qualifier("dieselEngine")
public class DieselEngine implements Engine {}

@Component
public class Car {
    @Autowired
    @Qualifier("electricEngine")
    private Engine engine;

    public void start() {
        System.out.println("Using engine: " + engine.getType());
    }
}

In this example:

    ElectricEngine and DieselEngine both implement Engine.
    @Qualifier ensures that the electricEngine bean is injected into Car.

Without @Qualifier, Spring would throw a NoUniqueBeanDefinitionException.

 * 
 */
/*
 * 
 * @Transactional is an annotation in Spring used to manage transactions declaratively. 
 * It applies to methods or classes to ensure that database operations are executed within a 
 * transaction. If any operation in the transaction fails, changes are rolled back 
 * to maintain data consistency.
 * 
 * @Transactional is an annotation in Spring used to manage transactions declaratively. It applies to methods or classes to ensure that database operations are executed within a transaction. If any operation in the transaction fails, changes are rolled back to maintain data consistency.
Core Features of @Transactional

    Declarative Transaction Management:
        Eliminates the need for manual transaction management with try-catch blocks.
        Ensures transactions are handled automatically based on configurations.

    Propagation Behavior:
        Defines how a method participates in an existing transaction or starts a new one.
        Common propagation types:
            REQUIRED (default): Joins the current transaction or creates a new one if none exists.
            REQUIRES_NEW: Suspends the existing transaction and starts a new one.
            SUPPORTS: Runs in a transaction if one exists; otherwise, executes non-transactionally.

    Isolation Level:
        Determines how transactions handle concurrent access to the database.
        Common levels:
            READ_COMMITTED: Prevents dirty reads.
            REPEATABLE_READ: Prevents dirty and non-repeatable reads.
            SERIALIZABLE: Ensures full isolation but reduces concurrency.

    Rollback Rules:
        By default, transactions roll back only for unchecked exceptions (RuntimeException).
        Custom rollback rules can be configured for specific exceptions using rollbackFor.

    Read-Only Transactions:
        Optimizes performance for queries by hinting that no data will be modified.


 */

 /*
 @Retryable is an annotation provided by Spring Retry, 
 a library that integrates with Spring to handle retries for failed operations. 
 It is commonly used to retry operations that fail due to transient issues,
  such as network connectivity problems or temporary service downtime.

  Core Features of @Retryable

    Automatic Retry:
        Retries a method if an exception is thrown.

    Configurable Parameters:
        maxAttempts: Number of retry attempts (default is 3).
        backoff: Defines the delay between retries. Supports fixed and exponential backoff strategies.
        value or include: Specifies the exceptions for which retries should occur.
        exclude: Specifies exceptions that should not trigger retries.


 Exponential Backoff:

    Delays increase exponentially with each retry.

@Retryable(value = {IOException.class}, maxAttempts = 5, backoff = @Backoff(delay = 1000, multiplier = 2))
public void performTaskWithExponentialBackoff() throws IOException {
    // Logic that may throw IOException
}


Handling Specific Exceptions:

    Include or exclude specific exceptions.

@Retryable(include = {IOException.class}, exclude = {FileNotFoundException.class})
public void performTask() throws IOException {
    // Logic that may throw IOException
}


Using @Recover for Recovery:

    Define a recovery method to handle failure after retries are exhausted.

    @Retryable(value = {IOException.class}, maxAttempts = 3)
    public void performTask() throws IOException {
        throw new IOException("Simulated failure");
    }

    @Recover
    public void recover(IOException e) {
        System.out.println("Recovering from exception: " + e.getMessage());
    }

@EnableTransactionManagement

    Purpose: Enables Spring's annotation-driven transaction management.
    Scope: Applied at the configuration level (usually on a @Configuration class).
    Functionality:
        Detects @Transactional annotations.
        Configures a transaction management mechanism (e.g., PlatformTransactionManager).
        Registers necessary infrastructure beans in the application context.
    Example:

@Configuration
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

You can change the scope of a bean by using the @Scope annotation, which defines how long a bean should exist and when it should be created. This can be combined with the @Bean method to customize the bean's lifecycle and visibility.
Bean Scopes in Spring:

    Singleton (default):
        Only one instance of the bean exists per Spring container.
        This is the default scope, which means the Spring container will create a single shared instance of the bean.

@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}

Prototype:

    New instance of the bean is created every time it's requested.
    Useful when you need a fresh object every time it is requested (e.g., for stateful objects).

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public MyService myService() {
        return new MyService();
    }
}

Request (only in a web application):

    A new bean is created for each HTTP request. This is useful in a web application to handle the scope of beans related to HTTP requests.

@Configuration
public class AppConfig {
    @Bean
    @Scope("request")
    public MyService myService() {
        return new MyService();
    }
}

Session (only in a web application):

    A new bean is created for each HTTP session. It's typically used for managing state within a user's session.

@Configuration
public class AppConfig {
    @Bean
    @Scope("session")
    public MyService myService() {
        return new MyService();
    }
}

Application (only in a web application):

    Similar to singleton, but it is specific to the web application context. The same instance of the bean will be shared across the entire web application.

    @Configuration
    public class AppConfig {
        @Bean
        @Scope("application")
        public MyService myService() {
            return new MyService();
        }
    }

How to Change Bean Scope Dynamically

Sometimes, you may want to control the scope programmatically. 
Spring's @Scope annotation can be combined with scoped proxy to create dynamic scopes, 
but this is more advanced and often used when you have more complex scenarios like managing 
the scope of beans based on the environment or state of the application.
Example of @Scope with a proxy mode:

@Configuration
public class AppConfig {
    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MyService myService() {
        return new MyService();
    }
}

This will create a proxy around the bean so that it behaves like a prototype even if 
the class is injected into singleton beans. The proxyMode is used to create a proxy 
for beans in specific scopes.


Here, databaseService will be a singleton by default.
What happens when you request a prototype bean from a singleton bean?

    Spring will inject a proxy for the prototype bean into the singleton bean to maintain the 
    lifecycle of the prototype bean
  * 

  A singleton bean can be used to manage a database connection pool because you want 
  to reuse the same instance throughout the application to optimize performance.

    Why Singleton?: The database connection pool is an expensive resource to create and maintain. 
    Recreating the connection pool on each request would introduce unnecessary overhead. 
    A singleton scope ensures that only one instance is created and used throughout the 
    entire application.

    Prototype Scope:

Use Case: Objects that should be created every time they are requested.

Example: User-Specific Request Processing

Imagine an application where you need to create a new user-specific 
request handler every time a user submits a request. Each request is
 independent and needs its own separate processing context.

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public UserRequestHandler userRequestHandler() {
        return new UserRequestHandler();
    }
}

    Why Prototype?: Each user request may have unique data and needs a separate instance 
    for processing. By using the prototype scope, you ensure that a new instance of 
    UserRequestHandler is created each time a request is made.


    3. Request Scope (Web Applications):

Use Case: Beans that should live for the duration of an HTTP request.

Example: Shopping Cart

In an e-commerce application, you could use a request-scoped bean to represent a shopping cart. 
Each user session will have its own shopping cart, and the cart should be available only for
 the duration of the user's request (e.g., the entire HTTP request cycle).


 Session Scope (Web Applications):

Use Case: Beans that should live for the duration of an HTTP session.

Example: User Profile

In a web application, a user profile can be session-scoped.
 This allows the user to store session-specific information such as user preferences, 
 authentication details, or preferences during their session.
 
 Application Scope (Web Applications):

Use Case: Beans that should live for the duration of the application.

Example: Application Configuration

An application configuration bean that holds global settings, such as environment variables or application-wide settings, can be application-scoped. This bean would be the same across the entire application and should remain available as long as the application is running.

@Configuration
public class AppConfig {

    @Bean
    @Scope("application")
    public AppConfigSettings appConfigSettings() {
        return new AppConfigSettings();
    }
}

    Why Application Scope?: The application-wide settings bean should be shared across the 
    entire application and not be tied to individual user sessions or requests. 
    This scope ensures that only one instance of AppConfigSettings exists during 
    the application lifecycle.


    1. String:

    Definition: String is an immutable class in Java. Once a String object is created, its value cannot be changed.
    Memory: Every time a new value is assigned to a String, a new String object is created, and the old one is discarded (due to immutability).
    Performance: Because of immutability, String is slower in scenarios involving frequent string modifications. Each modification involves creating a new String object.
    Use Case: Best used when the string is not going to change or when you're working with constants.

Example:

String str = "Hello";
str = str + " World";  // This creates a new String object

Why use String?

    It is thread-safe due to immutability.
    It is ideal for situations where the string value doesn’t change often (such as constants or configuration data).

2. StringBuilder:

    Definition: StringBuilder is a mutable sequence of characters. Unlike String, it can be modified directly without creating new objects.
    Thread Safety: StringBuilder is not thread-safe. It is designed for single-threaded environments where performance is critical.
    Memory: StringBuilder uses a dynamic array of characters that can grow and shrink as needed. It minimizes the creation of new objects and thus improves performance when manipulating strings.
    Performance: It is more efficient than String when you are performing frequent string concatenations or modifications because it doesn’t create new objects each time.
    Use Case: Best used in scenarios where you need to modify a string repeatedly, such as building strings dynamically.

Example:

StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");  // Modifies the same StringBuilder object

Why use StringBuilder?

    It is faster for string manipulations in single-threaded applications.
    Suitable for cases where the string content is frequently modified.

3. StringBuffer:

    Definition: StringBuffer is similar to StringBuilder in that it is mutable and allows modification of strings. However, it is designed to be thread-safe.
    Thread Safety: StringBuffer is synchronized, meaning it is thread-safe and can be used in multi-threaded environments where multiple threads might be modifying the same string.
    Memory: Like StringBuilder, StringBuffer uses a dynamic array to hold characters and allows for efficient modification of string content.
    Performance: StringBuffer is slower than StringBuilder due to synchronization overhead. If thread safety is not required, StringBuilder should be preferred for better performance.
    Use Case: Best used when you need to modify strings in multi-threaded environments and thread safety is a priority.

Example:

StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World");  // Modifies the same StringBuffer object

Why use StringBuffer?

    It is thread-safe and can be used in multi-threaded applications where multiple threads are accessing or modifying the same string.
    Use it when you need synchronized access to the string content


        What are some common thread-safety issues?
        Race Conditions: When two or more threads attempt to modify shared data at the same time.
        Deadlocks: When two or more threads are blocked forever, each waiting for the other to release a lock.
        Starvation: When a thread is indefinitely delayed due to other threads constantly acquiring the resources it needs.
        Livelocks: When two threads keep changing their states in response to each other, but neither makes progress.


What are the different ways to achieve thread safety in Java?

    Synchronized Methods: Using the synchronized keyword to control access to a method.
    Synchronized Blocks: Using the synchronized keyword inside a method to lock only a specific part of the code.
    Reentrant Locks: Using java.util.concurrent.locks.Lock to have more control over synchronization (e.g., trying to lock with timeouts or getting lock fairness).
    Atomic Classes: Using classes from java.util.concurrent.atomic package, like AtomicInteger, AtomicReference, to handle simple operations atomically.
    Thread-safe Collections: Using thread-safe collections like CopyOnWriteArrayList, ConcurrentHashMap, or using Collections.synchronizedList for existing collections.
    Volatile Keyword: Ensures visibility of changes made by one thread to other threads, making sure changes are immediately visible to all threads.


    What is a race condition, and how do you prevent it?

    Race Condition occurs when two or more threads simultaneously access shared data and at least one thread modifies it. This leads to unpredictable and incorrect behavior.
    Prevention:
        Use synchronized methods or blocks.
        Use atomic variables (e.g., AtomicInteger) for simple operations.
        Use thread-safe collections like ConcurrentHashMap.


        ThreadLocal provides each thread with its own independent copy of a variable, eliminating the need for synchronization in situations where each thread works with its own data.
Synchronization is needed when multiple threads access shared data, and thread safety must be ensured using locks or other mechanisms.

You have a shared resource that needs to be accessed by multiple threads. How would you make sure only one thread accesses it at a time?

    Use synchronized blocks or ReentrantLock to ensure mutual exclusion and allow only one thread to access the resource at a time.

How would you handle thread safety for a web application that uses session data shared between multiple users?

    Ensure that each user’s session data is thread-local or store it in a way that each thread is isolated (e.g., using separate session objects for each user). For shared resources, use synchronization or thread-safe collections.

    Thread-safe code ensures that shared data or resources are accessed by multiple threads in a way that avoids data corruption or unexpected behavior. In Java, thread safety can be achieved using:

    Synchronization (locks): Ensuring only one thread accesses a critical section at a time.
    Thread-safe classes: Using classes like AtomicInteger, ConcurrentHashMap, and Collections.synchronizedList.
    Immutable objects: Immutable data is inherently thread-safe as it cannot be changed once created.

 
    Example: Thread-Safe Increment Counter
Using synchronized

public class ThreadSafeCounter {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        ThreadSafeCounter counter = new ThreadSafeCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + counter.getCounter()); // Should be 2000
    }
}

Using AtomicInteger

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) {
        AtomicCounter counter = new AtomicCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + counter.getCounter()); // Should be 2000
    }
}

 */


 1. Making an ArrayList Thread-Safe Using Collections.synchronizedList

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SynchronizedListExample {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        Runnable addTask = () -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
        };

        Thread thread1 = new Thread(addTask);
        Thread thread2 = new Thread(addTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (list) {
            System.out.println("Synchronized List: " + list);
        }
    }
}


Thread-Safe Program Using ReentrantLock

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + example.getCount());
    }
}

3. Implementing a Thread-Safe Cache Using ConcurrentHashMap

import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeCache<K, V> {
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public static void main(String[] args) {
        ThreadSafeCache<String, String> cache = new ThreadSafeCache<>();

        Runnable writerTask = () -> {
            for (int i = 0; i < 10; i++) {
                cache.put("key" + i, "value" + i);
            }
        };

        Runnable readerTask = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Read: " + cache.get("key" + i));
            }
        };

        Thread writer = new Thread(writerTask);
        Thread reader = new Thread(readerTask);

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

4. Thread-Safe Singleton Class
Using synchronized

public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

Using Double-Checked Locking

public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

Using Enum for Singleton

public enum Singleton {
    INSTANCE;

    public void someMethod() {
        System.out.println("Singleton using Enum!");
    }
}


// 9.	Stack, vector, properties and hashtable = by default they are threadsafe as they 
//have internal synchronization.


concurrentHashMap and copyOnwriteArrayList
ChatGPT said:
ChatGPT
Topic: Achieving Synchronization in Collections

Synchronization ensures that only one thread accesses a resource at a time, preventing data inconsistency and race conditions. Java provides multiple ways to synchronize collections:
1. Using Collections.synchronized* Methods

The Collections utility class provides wrappers to synchronize collection objects, such as List, Set, and Map.
Key Methods:

    Collections.synchronizedList(List<T> list)
    Collections.synchronizedSet(Set<T> set)
    Collections.synchronizedMap(Map<K, V> map)

    These methods wrap the original collection, synchronizing access to the collection. 
    However, manual synchronization is needed for iteration.

    List<String> list = Collections.synchronizedList(new ArrayList<>());
    list.add("A");
    list.add("B");
    
    synchronized (list) { // Required during iteration
        for (String s : list) {
            System.out.println(s);
        }
    }

    Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
set.add(1);
set.add(2);

synchronized (set) { // Required during iteration
    for (Integer i : set) {
        System.out.println(i);
    }
}

Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
map.put(1, "One");
map.put(2, "Two");

synchronized (map) { // Required during iteration
    for (Map.Entry<Integer, String> entry : map.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}


Using Modern Concurrent Collections

//Java's java.util.concurrent package offers thread-safe alternatives to achieve better performance in highly concurrent environments. These alternatives avoid the need for external synchronization during iteration.
Concurrent Collections:

    ConcurrentHashMap: A thread-safe map implementation with segment-level locking for better performance.

Map<Integer, String> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put(1, "One");
concurrentMap.put(2, "Two");

for (Map.Entry<Integer, String> entry : concurrentMap.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}


CopyOnWriteArrayList: A thread-safe list where modifications (e.g., add, remove) create a new copy of the underlying array, ensuring safe iteration.

List<String> cowList = new CopyOnWriteArrayList<>();
cowList.add("A");
cowList.add("B");

for (String s : cowList) {
    System.out.println(s);
}

CopyOnWriteArraySet: A thread-safe set that works similarly to CopyOnWriteArrayList.

    Set<String> cowSet = new CopyOnWriteArraySet<>();
    cowSet.add("A");
    cowSet.add("B");

    for (String s : cowSet) {
        System.out.println(s);
    }


    When to Use ConcurrentHashMap vs Collections.synchronizedMap

// Choosing between ConcurrentHashMap and Collections.synchronizedMap depends on your application's concurrency requirements, performance expectations, and thread safety needs.
1. ConcurrentHashMap

A high-performance thread-safe map designed for scenarios with high concurrency and frequent read/write operations. It employs segment-level locking, allowing multiple threads to operate on different segments of the map simultaneously.
When to Use:

    High Concurrency: Multiple threads frequently read/write to the map.
    Performance-Critical Applications: When locking the entire map (as in synchronizedMap) would create bottlenecks.
    Read-Mostly Workloads: Optimized for scenarios with more reads than writes.

Example:

A web application caching user session data where concurrent threads frequently access and modify the map.

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Adding elements
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);

        // Accessing elements in multiple threads
        Runnable readTask = () -> {
            System.out.println("Value of A: " + concurrentMap.get("A"));
        };

        Runnable writeTask = () -> {
            concurrentMap.put("A", 3);
            System.out.println("Updated A to 3");
        };

        Thread thread1 = new Thread(readTask);
        Thread thread2 = new Thread(writeTask);

        thread1.start();
        thread2.start();
    }
}

2. Collections.synchronizedMap

A wrapper method that synchronizes an existing map. All methods of the synchronized map are synchronized on a single lock, so only one thread can access the map at a time.
When to Use:

    Low Concurrency: Few threads operate on the map.
  //  Legacy Code: When you're working with non-concurrent collections like HashMap and need to make them thread-safe.
   // Simpler Use Cases: When fine-grained control isn't necessary.

Example:

A single-threaded application that occasionally performs operations on a shared map across threads.

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedMapExample {
    public static void main(String[] args) {
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        // Adding elements
        synchronizedMap.put("X", 100);
        synchronizedMap.put("Y", 200);

        // Accessing elements
        synchronized (synchronizedMap) { // Explicit synchronization for iteration
            for (Map.Entry<String, Integer> entry : synchronizedMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}


/*
 * Basic Multithreading Concepts

    Thread: The smallest unit of a process that can execute independently.
    Process: A program in execution; contains multiple threads.
    Concurrency: Executing multiple tasks at the same time.
    Parallelism: Executing multiple tasks simultaneously on multiple processors.
 */

 /*
  * 
  Thread Lifecycle

    New: Thread object is created but not started.
    Runnable: Thread is ready to run but waiting for CPU time.
    Running: Thread is executing.
    Blocked/Waiting: Thread is paused, waiting for resources.
    Terminated: Thread has completed its task or has been stopped.

  */

  // Thread using Thread class
  class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }
}


public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.start(); // Start the thread
    }
}

// USING RUNNABLE INTERFACE

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}


// USING THE EXECUTOR FRAMEWORK
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Thread pool with 2 threads

        Runnable task1 = () -> System.out.println("Task 1 executed by: " + Thread.currentThread().getName());
        Runnable task2 = () -> System.out.println("Task 2 executed by: " + Thread.currentThread().getName());

        executor.execute(task1);
        executor.execute(task2);

        executor.shutdown(); // Gracefully shut down the executor
    }
}


Thread Safety

    Synchronized Blocks/Methods: Ensure only one thread accesses a block of code at a time.

public synchronized void increment() {
    counter++;
}

ReentrantLock: Explicit locking mechanism with more control than synchronized.

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void safeMethod() {
        lock.lock();
        try {
            System.out.println("Thread-safe method");
        } finally {
            lock.unlock();
        }
    }
}

Atomic Variables: Provides lock-free thread safety.

    import java.util.concurrent.atomic.AtomicInteger;

    public class AtomicExample {
        private AtomicInteger counter = new AtomicInteger();

        public void increment() {
            counter.incrementAndGet();
        }
    }

    ter-Thread Communication

    Threads can communicate using wait(), notify(), and notifyAll().
    Example: Producer-Consumer Problem

    class SharedResource {
        private boolean available = false;

        public synchronized void produce() throws InterruptedException {
            while (available) {
                wait();
            }
            available = true;
            System.out.println("Produced");
            notify();
        }

        public synchronized void consume() throws InterruptedException {
            while (!available) {
                wait();
            }
            available = false;
            System.out.println("Consumed");
            notify();
        }
    }

    What is Thread Pooling?

    Thread pooling involves creating a pool of threads that are reused to execute tasks, avoiding the overhead of creating and destroying threads repeatedly.
    Advantages
    
        Performance: Reduces the cost of thread creation/destruction.
        Scalability: Limits the number of concurrent threads to prevent resource exhaustion.
        Reusability: Threads are reused for multiple tasks.
    
    Code Example
    
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    
    public class ThreadPoolExample {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(3);
    
            for (int i = 1; i <= 5; i++) {
                int task = i;
                executor.execute(() -> System.out.println("Executing Task " + task + " by " + Thread.currentThread().getName()));
            }
    
            executor.shutdown();
        }
    }
    
    Difference Between wait() and sleep()
    Aspect	wait()	sleep()
    Belongs To	Object class.	Thread class.
    Lock Release	Releases the monitor lock.	Does not release the monitor lock.
    Usage Context	Used for thread communication.	Used to pause thread execution temporarily.
    Real-Life Use	Producer-Consumer problems.	Timer functionality.
    When to Use?
    
        Use wait() for thread communication when working with shared resources.
        Use sleep() for time-based delays or suspending thread execution.
    
    

        How to Avoid Deadlock:

        Lock Ordering: Ensure that all threads acquire locks in a fixed global order.
        Timeouts: Use tryLock() with a timeout to prevent indefinite waiting.
        Deadlock Detection: Periodically check for deadlocks in a multi-threaded application.

        Singleton Design Pattern

    Purpose: Ensures a class has only one instance and provides a global point of access to it.
    Real-life example: A database connection pool or a configuration manager.

Code Implementation:

public class Singleton {
    private static Singleton instance;

    private Singleton() {} // Private constructor

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

2. Factory Design Pattern

    Purpose: Creates objects without exposing the creation logic to the client. The client uses a common interface.
    Real-life example: A shape generator that creates circles, rectangles, etc., based on input.

Code Implementation:

interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        return null;
    }
}

public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape shape1 = factory.getShape("CIRCLE");
        shape1.draw();
        Shape shape2 = factory.getShape("RECTANGLE");
        shape2.draw();
    }
}

3. Observer Design Pattern

    Purpose: Defines a one-to-many dependency where one object (subject) notifies all its dependents (observers) of state changes.
    Real-life example: A news agency (subject) notifies subscribers (observers) of news updates.

Code Implementation:

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

class NewsAgency {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

public class ObserverPatternDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        Subscriber s1 = new Subscriber("Alice");
        Subscriber s2 = new Subscriber("Bob");

        agency.addObserver(s1);
        agency.addObserver(s2);

        agency.notifyObservers("Breaking News!");
    }
}

4. Builder Design Pattern

    Purpose: Simplifies the creation of complex objects by separating the construction process from its representation.
    Real-life example: Building a house where you have specific steps like setting walls, roof, and doors.

Code Implementation:

class House {
    private String walls;
    private String roof;
    private String doors;

    public void setWalls(String walls) { this.walls = walls; }
    public void setRoof(String roof) { this.roof = roof; }
    public void setDoors(String doors) { this.doors = doors; }

    @Override
    public String toString() {
        return "House [walls=" + walls + ", roof=" + roof + ", doors=" + doors + "]";
    }
}

class HouseBuilder {
    private House house;

    public HouseBuilder() {
        this.house = new House();
    }

    public HouseBuilder buildWalls(String walls) {
        house.setWalls(walls);
        return this;
    }

    public HouseBuilder buildRoof(String roof) {
        house.setRoof(roof);
        return this;
    }

    public HouseBuilder buildDoors(String doors) {
        house.setDoors(doors);
        return this;
    }

    public House build() {
        return house;
    }
}

public class BuilderPatternDemo {
    public static void main(String[] args) {
        House house = new HouseBuilder()
                .buildWalls("Brick Walls")
                .buildRoof("Concrete Roof")
                .buildDoors("Wooden Doors")
                .build();
        System.out.println(house);
    }
}

5. API Gateway

    Purpose: Acts as a single entry point for multiple services, often used in microservices architectures.
    Real-life example: Netflix API Gateway that routes requests to appropriate microservices.
    No specific Java code but frameworks like Spring Cloud Gateway can be used for API Gateway implementations.

6. Proxy Design Pattern

    Purpose: Provides a surrogate or placeholder to control access to an object.
    Real-life example: A credit card is a proxy for a bank account.

Code Implementation:

interface Internet {
    void connectTo(String serverHost) throws Exception;
}

class RealInternet implements Internet {
    public void connectTo(String serverHost) {
        System.out.println("Connecting to " + serverHost);
    }
}

class ProxyInternet implements Internet {
    private RealInternet internet = new RealInternet();
    private static List<String> bannedSites = List.of("abc.com", "xyz.com");

    public void connectTo(String serverHost) throws Exception {
        if (bannedSites.contains(serverHost.toLowerCase())) {
            throw new Exception("Access Denied to " + serverHost);
        }
        internet.connectTo(serverHost);
    }
}

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();
        try {
            internet.connectTo("google.com");
            internet.connectTo("abc.com");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

