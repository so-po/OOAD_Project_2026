# Conway's Game of Lives

Group Members: Melanie Porter & Sofia Poulsen

This project is conway's game of life (https://conwaylife.com/), extended to include several different creatures that follow different rulesets.

# How to Run: 

Run [Main.java](src/conwaygame/Main.java). 
To edit the grid, first enter in the cell's coordinates that you wish to edit, and then toggle its alive/dead state. 
Red cells are dead, and green cells are alive. Unpause the game to see it running in real time. 
*Note that the coordinates are zero-indexed!*

# TODO: 

**MVP:**
- [ ] Logic: Add different types of creatures to the grid
- [ ] Logic: figure out what to do about default cell-- make all cells the 'DefaultCreature' or have some sort of 'DeadCreature' that never comes alive? 
- [ ] UI: Make the grid UI able to display different types of creatures (e.g., with different colors)
- [ ] Grading: Ask if the facade pattern is sufficient for counting as a fourth pattern
- [ ] Logic: Write tests for everything
- [X] ~~Logic: Figure out why GameController thread only works when printing text. (???)~~
- [X] ~~UI: show whether the game is paused or unpaused~~
- [X] ~~Design: Create a wrapper for the ConwayGame, GameController and GameViewer~~
- [X] ~~Logic & UI: Make UI grid cells dynamically editable instead of hard-coded~~
- [X] ~~UI: Create an actual grid UI~~

**Stretch Goals:**
- [ ] Logic: Add a make random board button w/ input options (use the builder pattern for this? ( ô.ô )
- [ ] Logic: Make different types of creatures handle neighbour alive/dead counting differently (i.e., does the creature consider alive creatures of other types to be 'alive' or 'dead'?)
  - Possibly change to using the strategy pattern? (w/ one set of strategies for how alive/dead is counted, and another set of strategies for how )
- [ ] UI: Center the grid & make the UI dynamically resize.
- [ ] UI: Replace the text entry x, y coordinates to edit cells with being able to click on the grid 
- [ ] Logic: Make user interaction undo-able by setting up the command pattern & a queue system

# Mid-Project Review Requirements:

Design Patterns:
1. **Template Pattern:** We have several different types of creatures 
that follow almost exactly the same algorithm, with only adjustments to the logic. 
As such, we created an `AbstractCreature` superclass for all the creatures, who has a final `checkNeighborsAndSetState` function that uses the 
abstract `getMinimumNeighbours` and `getMaxNeighbours` hooks to define the logic that differs for each creature. 
In the future, we may further add  variation to the algorithm by changing whether 
each creature type considers other creature types to be valid 'alive' neighbours.

2. **Factory Pattern:** For looser coupling, the `Grid` class uses a creature factory to generate different types of creatures.
3. **Model View Control Pattern:** As we have a UI and a backend that need to send each other messages back and forth, we used an MVC pattern for this. 
   - The `Grid` class acts as the **Model** and makes decisions about the simulation's logic as well as stores its data. 
   - The `GameViewer` acts as the **Viewer** and is a Swing UI for the simulation and allows the user to edit the cell states and advance the simulation. 
   - The `GameController` acts as the **Controller**. It defines actions for when the buttons are pressed in the viewer. These actions ask the model to perform certain actions, and then updates the viewer accordingly.

OO Principle Examples: 
- Polymorphism: There are several different types of creatures that inherent from an `AbstractCreature` (DefaultCreature, ExplosiveCreature and ScarcityCreature). Only the DefaultCreature is used for now, but we plan to use all of them.
- Dependency Injection: The `GameController` takes in a view and a model.
Currently, it's very tightly coupled with the `Grid` and `GameViewer` classes, but we plan to make an interface for the gameviewer to generalize that a bit more and add the possibility of different UI's for the game.

Test Cases: [GridTest.java](test/conwaygame/GridTest.java)

# Reference:

Questions we have:
1. how to instantiate both gameviewer and grid with the grid size?
   - have a wrapper for the controller, viewer, and model that takes in inputs about how the game should be instantiated (this isn't a big problem)
2. which class should run the game? Should that be the controller, or the model?
   - for now, maybe just let the user edit the grid when the game is not playing, and then improve on this if needed
3. what to do for the fourth pattern
   - maybe have a singleton class that keeps track of game info like grid size
   - maybe use the builder pattern for problem # 1 for creating the grid
   - strategy pattern for class of other creatures that follows a different?


Model view controller reference:
- https://www.geeksforgeeks.org/system-design/mvc-design-pattern/
- https://www.youtube.com/watch?v=dTVVa2gfht8
- https://web.archive.org/web/20130726050302/https://www.newthinktank.com/2013/02/mvc-java-tutorial/

Conway's game of life original rules:
1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
