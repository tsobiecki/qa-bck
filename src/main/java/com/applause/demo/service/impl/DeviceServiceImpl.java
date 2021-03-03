package com.applause.demo.service.impl;

import com.applause.demo.dto.DeviceDto;
import com.applause.demo.repository.DeviceRepository;
import com.applause.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<DeviceDto> getDevices() {
        return deviceRepository.getDevices().stream()
                .map(device -> DeviceDto.builder()
                        .id(device.getId())
                        .deviceName(device.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
