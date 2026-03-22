# Conway's Game of Lives

Group Members: Melanie Porter & Sofia Poulsen

Conway's game of life original rules:
1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

Design Patterns:
- Template Pattern: AbstractCreature checkNeighborsAndSetState algorithm
- Factory Pattern: Creature factory
- Model View Control Pattern: Model - Grid. Controller - GameController. View - GameViewer


model view controller reference:
- https://www.geeksforgeeks.org/system-design/mvc-design-pattern/
- https://www.youtube.com/watch?v=dTVVa2gfht8

Issue I'm running in to: Who should run the game? Should that be the controller, or the model?
It's surely not the viewer, but that's what we currently have set up.