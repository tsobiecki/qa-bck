package com.applause.demo.repository.impl;

import com.applause.demo.entity.Device;
import com.applause.demo.repository.DeviceRepository;
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

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    @Override
    public List<Device> getDevices() {
        return getDevicesFromCsvFile();
    }

    private List<Device> getDevicesFromCsvFile() {
        File csvFile = new File("src/main/resources/devices.csv");
        List<Device> devices = new ArrayList<>();
        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new FileReader(csvFile))) {
            devices = in.lines().skip(1).map(line -> {
                if (line.isBlank()) {
                    return null;
                }
                String[] x = pattern.split((line.replaceAll("\"", "")));
                return Device.builder()
                        .id(Integer.parseInt(x[0]))
                        .description(x[1])
                        .build();
            })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return devices;
    }
}
