# DevCraft
Solution of task : Conway's Game of Life.

Overview
This project is a Spring Boot implementation of Conway's Game of Life. 
It simulates the game, where cells on a grid evolve over multiple iterations according to simple rules based on their neighbors.
This application exposes a REST API to accept input configurations for the game, including the initial seed state and the number of game iterations. 
It processes these inputs and returns the resulting grid after simulation.

How to test solution via Postman:
1. Run the program from GameOfLifeApplication
2 Create a HTTP POST Request at address : http://localhost:8080/api/gameoflife/simulate,
Select "Body" -> "raw" -> JSON
3.Example of a request body:
{
  "seed": [
    ["DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "ALIVE", "ALIVE", "DEAD"],
    ["DEAD", "ALIVE", "ALIVE", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD"]
  ],
  "iterations": 5
}

4. Test Cases that the solution was ran against:
------------------------------------------------
Test Case 1 Still Life Pattern (Block)
Input:
{
  "seed": [
    ["DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "ALIVE", "ALIVE", "DEAD"],
    ["DEAD", "ALIVE", "ALIVE", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD"]
  ],
  "iterations": 5
}

Expected Output:
{
  "result": [
    ["DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "ALIVE", "ALIVE", "DEAD"],
    ["DEAD", "ALIVE", "ALIVE", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD"]
  ]
}

Test Case 2: Oscillator Pattern (Blinker)
Input:
{
  "seed": [
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"]
  ],
  "iterations": 4
}

Expected Output:
{
  "result": [
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "ALIVE", "ALIVE", "ALIVE", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"]
  ]
}

Test Case 3: Glider (Spaceship)
{
  "seed": [
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "ALIVE", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"],
    ["ALIVE", "ALIVE", "ALIVE", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"]
  ],
  "iterations": 4
}

Expected Output:
{
  "result": [
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "ALIVE", "DEAD"],
    ["DEAD", "ALIVE", "ALIVE", "ALIVE", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD", "DEAD"]
  ]
}

Test Case 4: Empty Grid
{
  "seed": [
    ["DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD"]
  ],
  "iterations": 10
}

Expected Output:
{
  "result": [
    ["DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD"]
  ]
}

Test Case 5: Single Live Cell
{
  "seed": [
    ["DEAD", "DEAD", "DEAD"],
    ["DEAD", "ALIVE", "DEAD"],
    ["DEAD", "DEAD", "DEAD"]
  ],
  "iterations": 1
}

Expected Output:
{
  "result": [
    ["DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD"]
  ]
}

Test Case 6: Edge Case - All Alive Grid
{
  "seed": [
    ["ALIVE", "ALIVE", "ALIVE"],
    ["ALIVE", "ALIVE", "ALIVE"],
    ["ALIVE", "ALIVE", "ALIVE"]
  ],
  "iterations": 2
}

Expected Output (after 2 iterations):
{
  "result": [
    ["DEAD", "ALIVE", "DEAD"],
    ["ALIVE", "DEAD", "ALIVE"],
    ["DEAD", "ALIVE", "DEAD"]
  ]
}

Test Case 7: Large Still Life (Boat)
{
  "seed": [
    ["DEAD", "ALIVE", "ALIVE", "DEAD"],
    ["ALIVE", "DEAD", "DEAD", "ALIVE"],
    ["DEAD", "ALIVE", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD"]
  ],
  "iterations": 3
}

Expected Output:
{
  "result": [
    ["DEAD", "ALIVE", "ALIVE", "DEAD"],
    ["ALIVE", "DEAD", "DEAD", "ALIVE"],
    ["DEAD", "ALIVE", "DEAD", "DEAD"],
    ["DEAD", "DEAD", "DEAD", "DEAD"]
  ]
}

Test Case 8: Edge Case - Zero Iterations
Input:
{
  "seed": [
    ["DEAD", "ALIVE", "DEAD"],
    ["DEAD", "ALIVE", "DEAD"],
    ["DEAD", "ALIVE", "DEAD"]
  ],
  "iterations": 0
}

Expected Output:

{
  "result": [
    ["DEAD", "ALIVE", "DEAD"],
    ["DEAD", "ALIVE", "DEAD"],
    ["DEAD", "ALIVE", "DEAD"]
  ]
}
