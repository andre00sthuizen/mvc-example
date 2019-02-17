# Requirements:
* Java 8 or higher
* Maven to build
* Run <code>mvn clean install</code> inside the project.
* Run <code>java -jar ./target/assignment1-1.0.0.jar</code>
* Alternatively, the entry point of the application is the <code>com.andreoosthuizen.Main</code> class which can also be run from an IDE.

# Assumptions:
* I minimised the use of 3rd party libraries to demonstrate competency for this assignment.
* Some helpful libraries that could have been used:
   - [Spring Shell](https://projects.spring.io/spring-shell/)
   - [Spring MVC](https://spring.io/projects/spring-framework)
* When the input coordinates are out bounds they are ignored and a best effort is made to render.
* Drawables can draw on the entire Canvas, including on the borders of the canvas (if any)

# Test Coverage
A TDD approach was followed which resulted in good code coverage.

Test coverage reports are available in <code>${project.dir}/target/site/jacoco/index.html</code>
 
# Design
The <code>Main</code> class starts a <code>ConsoleApplication</code> in a thread and listens for user input.
The Command Pattern is used to delegate instructions to the <code>Controller</code>.
I have used a simple MVC pattern to separate the UI concerns.
The <code>Controller</code> updates the <code>Canvas</code> model object and listens for model updates via an Observer pattern using <code>java.beans.PropertyChangeSupport</code>

# Adding new Command
Commands are looked up at runtime with Java's built-in ServiceLoader SPI.
You could therefore add a new class that implements <code>Command</code> and append the fully qualified class name to <code>META-INF/services/com.andreoosthuizen.command.Command</code>.
It's easy to implement a new geometric shape by just proving a new Command implementation, provided it consists only of horizontal and vertical lines.

# SOLID principles
|                                                   | Single Responsibility | Open/closed Principle | Liskov Substitution | Interface Segregation | Dependency Inversion |
|---------------------------------------------------|-----------------------|-----------------------|---------------------|-----------------------|----------------------|
| com.andreoosthuizen.command.Command               |          N/A          |          N/A          |       N/A           |           OK          |         N/A          |
| com.andreoosthuizen.command.CommandFactory        |          OK           |          OK           |       OK            |           N/A         |         OK           |
| com.andreoosthuizen.command.CreateCommand         |          OK           |          OK           |       OK            |           N/A         |         OK           |
| com.andreoosthuizen.command.DrawLineCommand       |          OK           |          OK           |       OK            |           N/A         |         OK           |
| com.andreoosthuizen.command.DrawRectangleCommand  |          OK           |          OK           |       OK            |           N/A         |         OK           |
| com.andreoosthuizen.command.FillCommand           |          OK           |          OK           |       OK            |           N/A         |         OK           |
| com.andreoosthuizen.command.QuitCommand           |          OK           |          OK           |       OK            |           N/A         |         OK           |
|                                                   |                       |                       |                     |                       |                      |
| com.andreoosthuizen.controller.Controller         |          N/A          |          N/A          |       N/A           |           OK          |         N/A          |
| com.andreoosthuizen.controller.ControllerDefault  |          OK           |          OK           |       OK            |           N/A         |         OK           |
|                                                   |                       |                       |                     |                       |                      |
| com.andreoosthuizen.model.Canvas                  |          N/A          |          N/A          |       N/A           |           OK          |         N/A          |
| com.andreoosthuizen.model.CanvasDefault           |          OK           |          OK           |       OK            |           N/A         |         OK           |
| com.andreoosthuizen.model.Drawable                |          N/A          |          N/A          |       N/A           |           OK          |         N/A          |
| com.andreoosthuizen.model.Fill                    |          OK           |          OK           |       OK            |           N/A         |         OK           |
| com.andreoosthuizen.model.Line                    |          OK           |          OK           |       OK            |           N/A         |         OK           |
| com.andreoosthuizen.model.Raster                  |          N/A          |          N/A          |       N/A           |           OK          |         N/A          |
| com.andreoosthuizen.model.RasterDefault           |          OK           |          OK           |       OK            |           N/A         |         OK           |
|                                                   |                       |                       |                     |                       |                      |
| com.andreoosthuizen.view.View                     |          N/A          |          N/A          |       N/A           |           OK          |         N/A          |
| com.andreoosthuizen.view.ViewDefault              |          OK           |          OK           |       OK            |           N/A         |         OK           |
|                                                   |                       |                       |                     |                       |                      |
| com.andreoosthuizen.DrawApplication               |          -            |          -            |       -             |           -           |         -            |
| com.andreoosthuizen.Main                          |          N/A          |          N/A          |       N/A           |           N/A         |         N/A          |


See the ProblemStatement.txt file in this project for more details. 