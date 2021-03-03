package com.applause.demo.repository.impl;

import com.applause.demo.entity.Bug;
import com.applause.demo.entity.Device;
import com.applause.demo.entity.Tester;
import com.applause.demo.repository.BugRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Repository
public class BugRepositoryImpl implements BugRepository {

    @Override
    public HashMap<Integer, Integer> getBugsBy(List<Integer> testersIds, Set<Integer> devicesIds) {
        HashMap<Integer, Integer> newHashMap = new HashMap<>();
        testersIds.forEach(id -> newHashMap.put(id, 0));
        getBugsFromCsvFile().stream()
                .filter(bug -> (testersIds.contains(bug.getTester().getId()) && devicesIds.contains(bug.getDevice().getId())))
                .forEach(bug -> {
                    Integer bugValue = newHashMap.get(bug.getTester().getId());
                    newHashMap.put(bug.getTester().getId(), bugValue + 1);
                });
        return newHashMap;
    }

    private List<Bug> getBugsFromCsvFile() {
        File csvFile = new File("src/main/resources/bugs.csv");
        List<Bug> bugs = new ArrayList<>();
        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new FileReader(csvFile))) {
            bugs = in.lines().skip(1).map(line -> {
                if (line.isBlank()) {
                    return null;
                }
                String[] x = pattern.split((line.replaceAll("\"", "")));
                return Bug.builder()
                        .id(Integer.parseInt(x[0]))
                        .tester(Tester.builder().id(Integer.parseInt(x[1])).build())
                        .device(Device.builder().id(Integer.parseInt(x[2])).build())
                        .build();
            })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bugs;
    }

}
