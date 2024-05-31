package com.applause.demo.service.impl;

import com.applause.demo.entity.Cell;
import com.applause.demo.repository.CellRepository;
import com.applause.demo.service.CellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CellServiceImpl implements CellService {

    @Autowired
    private CellRepository cellRepository;

    @Override
    public List<Cell> filterByRange(int from, int to) {
        return cellRepository.getCellsByRange(from, to);
    }

    @Override
    public List<Cell> filterByList(String type) {
        return cellRepository.getCellsByType(type);
    }

    @Override
    public void filterBySomething() {

    }
}
