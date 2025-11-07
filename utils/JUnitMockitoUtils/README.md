# JUnitMockitoUtils
Utils for JUnit and Mockito

* JUnit 5

JUnit Platform serves as a foundation for launching testing frameworks on the JVM.
JUnit 5 requires Java 8 (or higher) at runtime.

https://junit.org/junit5/docs/snapshot/user-guide/
Nested Tests
Running Tests in IDE

* JUnit 5 vs JUnit 4
  Assertions in JUnit 4 are in org.junit.Assert class, but in JUnit 5 are in org.junit.jupiter.api.Assertions.

- JUnit 5:
  It is recommended to omit the public modifier for test classes, test methods, and lifecycle methods.

https://www.geeksforgeeks.org/junit-5-vs-junit-4/

* JUnit Jupiter API

JUnit5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

JUnit Tutorial
https://www.w3schools.blog/junit-tutorial
https://www.vogella.com/tutorials/JUnit/article.html

* Dependencies in Maven pom.xml

Go to mvnrepository.com

- JUnit:
  import org.junit.Test;

junit-jupiter-api
junit-jupiter-engine
junit-jupiter-params

<dependency>
	<groupId>org.junit.jupiter</groupId>
	<artifactId>junit-jupiter</artifactId> // JUnit Jupiter (Aggregator), it contains all dependencies
	<version>5.6.2</version>
	<scope>test</scope>
</dependency>

- JUnit AssertJ library (for assertions):

<dependency>
	<groupId>org.assertj</groupId>
	<artifactId>assertj-core</artifactId>
	<version>3.17.2</version>
	<scope>test</scope>
</dependency>

- Mockito:
  import org.mockito.Mock;

mockito-core (new) and mockito-all (discontinued) are the same

    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-all</artifactId>
        <version>1.10.19</version>
        <scope>test</scope>
    </dependency>

or:

Mockito JUnit Jupiter: mockito-junit-jupiter // set before junit dependency, to use @Mock annotations
@ExtendWith(MockitoExtension.class) // to enable to @Mock annotation

	<dependency>
		<groupId>org.mockito</groupId>
		<artifactId>mockito-junit-jupiter</artifactId>
		<scope>test</scope>
	</dependency>

**************************************************

* Build Test with Maven

Go Terminal

Go path of project

command: ls

command: mvn package

command: mvn test // run only test
or: mvn clean test // clean target directory

- To execute unit test during Maven build lifecycle we need install plugin in pom.xml: Maven Surefire Plugin (search in mvnrepository.com):

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.2.2</version>
        </plugin>
    </plugins>
</build>

- Run project without test:
  mvn package -Dmaven.test.skip=true

**************************************************

* Code Coverage

1. Export Test Report using Maven:

add plugin in pom.xml:
Maven Surefire Report Plugin: maven-surefire-report-plugin

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M6</version>
            <configuration>
                <testFailureIgnore>true</testFailureIgnore>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-report-plugin</artifactId>
            <version>3.0.0-M6</version>
            <executions>
                <execution>
                    <phase>test</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

mvn clean test // the report is generated in: target/site/surefire-report.html

go to folder UserService/target/site/jacoco/index.html

mvn site -DgenerateReports=false // generates report more nicer

2. Maven plugin for Code Coverage:

add plugin in pom.xml:
JaCoCo Maven Plugin: jacoco-maven-plugin
// JaCoCo = Java Code Coverage

<build>
    <plugins>
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.8</version>
            <executions>
                <execution>
                    <id>prepare-agent</id>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <execution>
                    <id>report</id>
                    <phase>test</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

mvn clean test // the report is generated in: target\site\jacoco\index.html

mvn site -DgenerateReports=false // generates report more nicer

**************************************************

* Test location
  For Maven and Gradle:
  src/main/java: for Java classes
  src/test/java: for test classes

* Create a Unit Test in Eclipse IDE
  Rigth click in the class, New/Other, then choose Java/JUnit/JUnit Test Case.

* Naming conventions

For Maven:
**/Test*.java: includes all of its subdirectories and all Java filenames that start with Test.
**/*Test.java: includes all of its subdirectories and all Java filenames that end with Test.
