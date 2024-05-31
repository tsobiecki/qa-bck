package com.applause.demo.repository.impl;

import com.applause.demo.entity.Cell;
import com.applause.demo.repository.CellRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.applause.demo.entity.Cell.Radio;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Repository
public class CellRepositoryImpl implements CellRepository {

    @Override
    public List<Cell> getCells() {
        return getCellsFromCsvFile();
    }

    @Override
    public List<Cell> getCellsByRange(int from, int to) {
        return getCells().stream()
                .filter(cell -> cell.getRange() >= from && cell.getRange() <= to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cell> getCellsByType(String type) {
        return getCells().stream()
                .filter(cell -> cell.getRadio().equals(Radio.valueOf(type)))
                .collect(Collectors.toList());
    }

    private List<Cell> getCellsFromCsvFile() {
        File csv = new File("src/main/resources/424.csv");
        List<Cell> cells = new ArrayList<>();
        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new FileReader(csv))) {
            cells = in.lines().skip(1).map(line -> {
                if (line.isBlank()) {
                    return null;
                }
                String[] x = pattern.split((line.replaceAll("\"", "")));

                return Cell.builder()
                        .radio(Radio.valueOf(x[0]))
                        .mcc(parseInt(x[1]))
                        .net(parseInt(x[2]))
                        .area(parseInt(x[3]))
                        .cell(parseInt(x[4]))
                        .unit(parseInt(x[5]))
                        .lon(parseDouble(x[6]))
                        .lat(parseDouble(x[7]))
                        .range(parseInt(x[8]))
                        .samples(parseInt(x[9]))
                        .changeable(parseBoolean(x[10]))
                        .created(parseLong(x[11]))
                        .updated(parseLong(x[12]))
                        .averageSignal(parseInt(x[13]))
                        .build();
            })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cells;
    }
}
