package com.example.demo;

import com.example.demo.dto.SimulationRequestDTO;
import com.example.demo.dto.SimulationResponseDTO;
import com.example.demo.service.GameOfLifeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GameOfLifeServiceTest {
    private final GameOfLifeService service = new GameOfLifeService();

    @Test
    void testStillLife_BlockPattern() {
        String[][] seed = {
                {"DEAD", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "ALIVE", "ALIVE", "DEAD"},
                {"DEAD", "ALIVE", "ALIVE", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "DEAD"}
        };

        SimulationRequestDTO request = new SimulationRequestDTO(seed, 5); // 5 iterations
        SimulationResponseDTO response = service.simulateGameOfLife(request);

        String[][] expected = {
                {"DEAD", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "ALIVE", "ALIVE", "DEAD"},
                {"DEAD", "ALIVE", "ALIVE", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "DEAD"}
        };

        assertArrayEquals(expected, response.getResult());
    }

    @Test
    void testOscillator_BlinkerPattern() {
        String[][] seed = {
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"}
        };

        SimulationRequestDTO request = new SimulationRequestDTO(seed, 2); // 2 iterations
        SimulationResponseDTO response = service.simulateGameOfLife(request);

        String[][] expected = {
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "ALIVE", "ALIVE", "ALIVE", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"}
        };

        assertArrayEquals(expected, response.getResult());
    }

    @Test
    void testGliderPattern() {
        String[][] seed = {
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "ALIVE", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"},
                {"ALIVE", "ALIVE", "ALIVE", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"}
        };

        SimulationRequestDTO request = new SimulationRequestDTO(seed, 4); // 4 iterations
        SimulationResponseDTO response = service.simulateGameOfLife(request);

        String[][] expected = {
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "ALIVE", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "ALIVE", "DEAD"},
                {"DEAD", "ALIVE", "ALIVE", "ALIVE", "DEAD"},
                {"DEAD", "DEAD", "DEAD", "DEAD", "DEAD"}
        };

        assertArrayEquals(expected, response.getResult());
    }

    @Test
    void testEmptyGrid() {
        String[][] seed = {
                {"DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD"}
        };

        SimulationRequestDTO request = new SimulationRequestDTO(seed, 10); // 10 iterations
        SimulationResponseDTO response = service.simulateGameOfLife(request);

        String[][] expected = {
                {"DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD"}
        };

        assertArrayEquals(expected, response.getResult());
    }

    @Test
    void testSingleLiveCellDies() {
        String[][] seed = {
                {"DEAD", "DEAD", "DEAD"},
                {"DEAD", "ALIVE", "DEAD"},
                {"DEAD", "DEAD", "DEAD"}
        };

        SimulationRequestDTO request = new SimulationRequestDTO(seed, 1); // 1 iteration
        SimulationResponseDTO response = service.simulateGameOfLife(request);

        String[][] expected = {
                {"DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD"},
                {"DEAD", "DEAD", "DEAD"}
        };

        assertArrayEquals(expected, response.getResult());
    }

    @Test
    void testAllCellsAlive() {
        String[][] seed = {
                {"ALIVE", "ALIVE", "ALIVE"},
                {"ALIVE", "ALIVE", "ALIVE"},
                {"ALIVE", "ALIVE", "ALIVE"}
        };

        SimulationRequestDTO request = new SimulationRequestDTO(seed, 2); // 2 iterations
        SimulationResponseDTO response = service.simulateGameOfLife(request);

        String[][] expected = {
                {"DEAD", "ALIVE", "DEAD"},
                {"ALIVE", "DEAD", "ALIVE"},
                {"DEAD", "ALIVE", "DEAD"}
        };

        assertArrayEquals(expected, response.getResult());
    }

    @Test
    void testZeroIterations() {
        String[][] seed = {
                {"DEAD", "ALIVE", "DEAD"},
                {"DEAD", "ALIVE", "DEAD"},
                {"DEAD", "ALIVE", "DEAD"}
        };

        SimulationRequestDTO request = new SimulationRequestDTO(seed, 0); // 0 iterations
        SimulationResponseDTO response = service.simulateGameOfLife(request);

        String[][] expected = {
                {"DEAD", "ALIVE", "DEAD"},
                {"DEAD", "ALIVE", "DEAD"},
                {"DEAD", "ALIVE", "DEAD"}
        };

        assertArrayEquals(expected, response.getResult());
    }
}
