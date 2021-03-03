package com.applause.demo.repository.impl;

import com.applause.demo.repository.TesterRepository;
import com.applause.demo.entity.Bug;
import com.applause.demo.entity.Device;
import com.applause.demo.entity.Tester;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Repository
public class TesterRepositoryImpl implements TesterRepository {

    @Override
    public List<Tester> getTesters(List<String> countries) {
        return new ArrayList<>(getTestersFromCsv());
    }

    @Override
    public Set<String> getTestersCountries() {
        return getTestersFromCsv().stream()
                .map(Tester::getCountry)
                .collect(Collectors.toSet());
    }

    private List<Tester> getTestersFromCsv() {
        File csvFile = new File("src/main/resources/testers.csv");
        List<Tester> testers = new ArrayList<>();
        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new FileReader(csvFile));) {
            testers = in.lines().skip(1).map(line -> {
                if (line.isBlank()) {
                    return null;
                }
                String[] x = pattern.split((line.replaceAll("\"", "")));
                return Tester.builder()
                        .id(Integer.parseInt(x[0]))
                        .firstName(x[1])
                        .lastName(x[2])
                        .country(x[3])
                        .lastLogin(convertedDate(x[4]))
                        .build();
            })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testers;
    }

    private Date convertedDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new Date(formatter.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
