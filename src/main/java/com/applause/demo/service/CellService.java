package com.applause.demo.service;

import com.applause.demo.entity.Cell;

import java.util.List;

public interface CellService {

    List<Cell> filterByRange(int from, int to);

    List<Cell> filterByList(String type);

    void filterBySomething();
}
