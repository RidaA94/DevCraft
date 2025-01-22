package com.example.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@RequiredArgsConstructor
public class Cell {
    private final int x;
    private final int y;
}
