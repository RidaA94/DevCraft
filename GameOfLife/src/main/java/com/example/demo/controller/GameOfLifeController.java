package com.example.demo.controller;

import com.example.demo.dto.SimulationRequestDTO;
import com.example.demo.dto.SimulationResponseDTO;
import com.example.demo.service.GameOfLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gameoflife")
public class GameOfLifeController {
    private final GameOfLifeService gameOfLifeService;

    @Autowired
    public GameOfLifeController(GameOfLifeService gameOfLifeService) {
        this.gameOfLifeService = gameOfLifeService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<SimulationResponseDTO> simulate(@RequestBody SimulationRequestDTO request) {
        try {
            SimulationResponseDTO response = gameOfLifeService.simulateGameOfLife(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimulationResponseDTO(new String[][]{{"ERROR: " + e.getMessage()}}));
        }
    }
}
