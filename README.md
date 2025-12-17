# Spring Framework - XML Configuration Example

A simple Java application demonstrating Spring Framework's Inversion of Control (IoC) and Dependency Injection using XML-based configuration.

## Overview

This project shows how to:
- Configure Spring beans using XML
- Use ApplicationContext to manage beans
- Retrieve and use Spring-managed objects
- Inject properties into beans

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Spring Framework libraries
- Maven or Gradle (recommended)

## Project Structure

```
project/
├── src/
│   └── main/
│       ├── java/
│       │   └── org/teju/
│       │       ├── App.java
│       │       └── Developer.java
│       └── resources/
│           └── applicationContext.xml
└── pom.xml (or build.gradle)
```

## Dependencies

### Maven Dependencies

Add these to your `pom.xml`:

```xml
<dependencies>
    <!-- Spring Context -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.1.0</version>
    </dependency>
    
    <!-- Spring Core -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>6.1.0</version>
    </dependency>
    
    <!-- Spring Beans -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>6.1.0</version>
    </dependency>
</dependencies>
```

### Gradle Dependencies

```gradle
dependencies {
    implementation 'org.springframework:spring-context:6.1.0'
    implementation 'org.springframework:spring-core:6.1.0'
    implementation 'org.springframework:spring-beans:6.1.0'
}
```

## Required Files

### 1. Developer.java (Bean Class)

Create the `Developer.java` class in `src/main/java/org/teju/`:

```java
package org.teju;

public class Developer {
    private int age;
    private String name;
    private Computer computer;  // Optional: for dependency injection
    
    // Default constructor
    public Developer() {
        System.out.println("Developer object created");
    }
    
    // Parameterized constructor
    public Developer(int age, String name) {
        this.age = age;
        this.name = name;
    }
    
    // Getters and Setters
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Computer getComputer() {
        return computer;
    }
    
    public void setComputer(Computer computer) {
        this.computer = computer;
    }
    
    // Business method
    public void code() {
        System.out.println("Developer is coding...");
        if (computer != null) {
            computer.compile();
        }
    }
    
    @Override
    public String toString() {
        return "Developer{age=" + age + ", name='" + name + "'}";
    }
}
```

### 2. applicationContext.xml (Spring Configuration)

Create this file in `src/main/resources/`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Basic Bean Definition with Property Injection -->
    <bean id="developer" class="org.teju.Developer">
        <property name="age" value="25"/>
        <property name="name" value="John Doe"/>
    </bean>
    
    <!-- Alternative: Constructor Injection -->
    <!--
    <bean id="developer" class="org.teju.Developer">
        <constructor-arg value="25"/>
        <constructor-arg value="John Doe"/>
    </bean>
    -->
    
    <!-- Optional: Bean with dependency injection -->
    <!--
    <bean id="computer" class="org.teju.Computer"/>
    
    <bean id="developer" class="org.teju.Developer">
        <property name="age" value="25"/>
        <property name="name" value="John Doe"/>
        <property name="computer" ref="computer"/>
    </bean>
    -->
</beans>
```

## How It Works

### 1. Spring Container Initialization

```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
```

This line:
- Creates the Spring IoC container
- Reads the XML configuration file from the classpath
- Instantiates all beans defined in the XML

### 2. Bean Retrieval

```java
Developer obj = context.getBean("developer", Developer.class);
```

Two ways to retrieve beans:
- **Type-safe way**: `context.getBean("developer", Developer.class)`
- **With casting**: `(Developer) context.getBean("developer")`

### 3. Using the Bean

```java
System.out.println(obj.getAge());  // Prints: 25
obj.code();                         // Prints: Developer is coding...
```

The bean is fully initialized with properties injected from XML.

## Running the Application

### Using Maven:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="org.teju.App"
```

### Using Gradle:

```bash
gradle clean build
gradle run
```

### Using IDE:

1. Import the project
2. Ensure dependencies are downloaded
3. Run `App.java` as a Java application

## Expected Output

```
Developer object created
25
Developer is coding...
```

## Key Concepts Explained

### Inversion of Control (IoC)

Instead of creating objects manually (`new Developer()`), Spring creates and manages them for you.

**Traditional Approach:**
```java
Developer obj = new Developer();
obj.setAge(25);
obj.setName("John");
```

**Spring Approach:**
```java
Developer obj = context.getBean("developer", Developer.class);
// Age and name are already set by Spring!
```

### Dependency Injection (DI)

Spring automatically injects dependencies defined in XML:

#### Property Injection (Setter-based)
```xml
<bean id="developer" class="org.teju.Developer">
    <property name="age" value="25"/>
</bean>
```
Calls `setAge(25)` automatically.

#### Constructor Injection
```xml
<bean id="developer" class="org.teju.Developer">
    <constructor-arg value="25"/>
    <constructor-arg value="John Doe"/>
</bean>
```
Calls `new Developer(25, "John Doe")` automatically.

### Bean Scopes

Add scope attribute to control bean lifecycle:

```xml
<!-- Singleton (default) - one instance per container -->
<bean id="developer" class="org.teju.Developer" scope="singleton">
    <property name="age" value="25"/>
</bean>

<!-- Prototype - new instance every time -->
<bean id="developer" class="org.teju.Developer" scope="prototype">
    <property name="age" value="25"/>
</bean>
```

## Advanced Configuration Examples

### 1. Injecting Collections

```xml
<bean id="developer" class="org.teju.Developer">
    <property name="age" value="25"/>
    <property name="skills">
        <list>
            <value>Java</value>
            <value>Spring</value>
            <value>Hibernate</value>
        </list>
    </property>
</bean>
```

### 2. Injecting Other Beans (Dependencies)

```xml
<bean id="laptop" class="org.teju.Laptop">
    <property name="brand" value="Dell"/>
</bean>

<bean id="developer" class="org.teju.Developer">
    <property name="age" value="25"/>
    <property name="computer" ref="laptop"/>
</bean>
```

### 3. Bean Lifecycle Methods

```xml
<bean id="developer" class="org.teju.Developer" 
      init-method="init" 
      destroy-method="cleanup">
    <property name="age" value="25"/>
</bean>
```

## Migration to Annotation-Based Configuration

Modern Spring applications use annotations instead of XML:

### Using @Component and @Autowired

```java
@Component
public class Developer {
    @Value("25")
    private int age;
    
    @Autowired
    private Computer computer;
    
    public void code() {
        System.out.println("Coding...");
    }
}
```

### Using Java Configuration

```java
@Configuration
public class AppConfig {
    @Bean
    public Developer developer() {
        Developer dev = new Developer();
        dev.setAge(25);
        return dev;
    }
}
```

## Troubleshooting

### FileNotFoundException: applicationContext.xml

**Solution**: Ensure the XML file is in `src/main/resources/` directory.

### NoSuchBeanDefinitionException

**Solution**: 
- Check bean id matches in XML and code
- Verify class name is correct in XML
- Ensure XML is in the classpath

### BeanCreationException

**Solution**:
- Verify class has a default constructor (for property injection)
- Check all setter methods exist for properties
- Ensure property types match values in XML

### ClassCastException

**Solution**: Use type-safe retrieval:
```java
// Instead of: Developer obj = (Developer) context.getBean("developer");
// Use: 
Developer obj = context.getBean("developer", Developer.class);
```

## Best Practices

1. **Use Interfaces**: Program to interfaces, not implementations
2. **Minimize XML**: Consider annotation-based or Java configuration for modern apps
3. **Bean Naming**: Use meaningful, consistent bean IDs
4. **Scope Selection**: Use singleton for stateless beans, prototype for stateful
5. **Resource Management**: Close ApplicationContext in production code
6. **Testing**: Use Spring's testing framework for unit tests

## Complete Example with Dependency

Here's a complete working example with dependencies:

**Computer.java:**
```java
package org.teju;

public class Computer {
    public void compile() {
        System.out.println("Compiling code...");
    }
}
```

**Updated applicationContext.xml:**
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="computer" class="org.teju.Computer"/>
    
    <bean id="developer" class="org.teju.Developer">
        <property name="age" value="25"/>
        <property name="name" value="John Doe"/>
        <property name="computer" ref="computer"/>
    </bean>
</beans>
```

## Additional Resources

- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)
- [Spring IoC Container](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans)
- [Spring Bean Scopes](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes)
- [Migrating from XML to Annotations](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-annotation-config)

## License

This is an educational example demonstrating Spring Framework XML configuration.
