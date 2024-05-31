package com.applause.demo.repository;

import com.applause.demo.entity.Cell;

import java.util.List;

public interface CellRepository {

    List<Cell> getCells();

    List<Cell> getCellsByRange(int from, int to);

    List<Cell> getCellsByType(String type);
}
