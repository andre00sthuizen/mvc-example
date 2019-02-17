# Requirements:
* Java 8 or higher
* Maven to build

The application should be run using the <code>com.andreoosthuizen.Main</code> class.

# Assumptions:
* I minimised the use of 3rd party libraries to demonstrate competency for this assignment.
* Some helpful libraries that could have been used:
   - [Spring Shell](https://projects.spring.io/spring-shell/)
   - [Spring MVC](https://spring.io/projects/spring-framework)
* When the input coordinates are out bounds they are ignored and a best effort render is made.

# Test Coverage
A TDD approach was followed which resulted in good code coverage.

TODO Show Reports
 
# Design
The <code>Main</code> class starts a <code>ConsoleApplication</code> in a thread and listens for user input.
The Command Pattern is used to delegate instructions to the <code>Controller</code>.
I have used a simple MVC pattern to separate the UI concerns. The <code>Controller</code> updates the <code>Canvas</code>
model object and listens for model updates via an Observer pattern using <code>java.beans.PropertyChangeSupport</code>

# TODO
* Adding new Command
* Adding new geometric shape in the model
* Adding new View
* SOLID principles...

|         | Single Responsibility | Open/closed Principle | Liskov Substitution | Interface Segregation | Dependency Inversion |
|---------|-----------------------|-----------------------|---------------------|-----------------------|----------------------|
| Class A |                       |                       |                     |                       |                      |
|         |                       |                       |                     |                       |                      |
|         |                       |                       |                     |                       |                      |



See the ProblemStatement.txt file in this project for more details. 