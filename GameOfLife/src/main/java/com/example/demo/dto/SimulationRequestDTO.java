package com.example.demo.dto;

import lombok.Data;

@Data
public class SimulationRequestDTO {
    private String[][] seed;
    private int iterations;

    //for testing purposes
    public SimulationRequestDTO(String[][] seed, int i) {
        this.seed = seed;
        this.iterations = i;
    }
}
