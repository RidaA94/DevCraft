package com.example.demo.service;

import com.example.demo.dto.SimulationRequestDTO;
import com.example.demo.dto.SimulationResponseDTO;
import com.example.demo.model.Cell;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GameOfLifeService {
    public SimulationResponseDTO simulateGameOfLife(SimulationRequestDTO request) {
        String[][] seedGrid = request.getSeed();
        int iterations = request.getIterations();
        Set<Cell> liveCells = gridToCellSet(seedGrid);

        for (int i = 0; i < iterations; i++) {
            liveCells = calculateNextGeneration(liveCells);
        }

        int[] bounds = calculateBounds(liveCells);

        String[][] resultGrid = cellSetToGrid(liveCells, bounds);

        return new SimulationResponseDTO(resultGrid);
    }

    private int[] calculateBounds(Set<Cell> liveCells) {
        if (liveCells.isEmpty()) {
            return new int[] { 0, 0, 0, 0 };
        }

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (Cell cell : liveCells) {
            minX = Math.min(minX, cell.getX());
            minY = Math.min(minY, cell.getY());
            maxX = Math.max(maxX, cell.getX());
            maxY = Math.max(maxY, cell.getY());
        }

        return new int[] { minX - 1, minY - 1, maxX + 1, maxY + 1}; // Add one-cell buffer
    }

    public String[][] cellSetToGrid(Set<Cell> liveCells, int[] bounds) {
        int minX = bounds[0], minY = bounds[1];
        int rows = bounds[2] - bounds[0] + 1;
        int cols = bounds[3] - bounds[1] + 1;

        if (rows <= 0 || cols <= 0) {
            return new String[0][0];
        }

        String[][] grid = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = "DEAD";
            }
        }

        for (Cell cell : liveCells) {
            int x = cell.getX() - minX;
            int y = cell.getY() - minY;
            if (x >= 0 && x < rows && y >= 0 && y < cols) {
                grid[x][y] = "ALIVE";
            }
        }

        return grid;
    }

    public Set<Cell> calculateNextGeneration(Set<Cell> liveCells) {
        Set<Cell> nextGeneration = new HashSet<>();
        Set<Cell> candidates = new HashSet<>();

        for (Cell cell : liveCells) {
            candidates.add(cell);
            candidates.addAll(getNeighbors(cell));
        }

        for (Cell cell : candidates) {
            int liveNeighbors = countLiveNeighbors(cell, liveCells);

            if (liveCells.contains(cell)) {
                if (liveNeighbors == 2 || liveNeighbors == 3) {
                    nextGeneration.add(cell);
                }
            } else if (liveNeighbors == 3) {
                nextGeneration.add(cell);
            }
        }
        return nextGeneration;
    }

    private int countLiveNeighbors(Cell cell, Set<Cell> liveCells) {
        int liveNeighborCount = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                if (liveCells.contains(new Cell(cell.getX() + dx, cell.getY() + dy))) {
                    liveNeighborCount++;
                }
            }
        }
        return liveNeighborCount;
    }

    private Set<Cell> getNeighbors(Cell cell) {
        Set<Cell> neighbors = new HashSet<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                neighbors.add(new Cell(cell.getX() + dx, cell.getY() + dy));
            }
        }
        return neighbors;
    }

    public Set<Cell> gridToCellSet(String[][] grid) {
        Set<Cell> liveCells = new HashSet<>();
        if (grid == null || grid.length == 0) return liveCells;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if ("ALIVE".equalsIgnoreCase(grid[x][y])) {
                    liveCells.add(new Cell(x, y));
                }
            }
        }
        return liveCells;
    }
}
