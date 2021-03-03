package com.applause.demo.service;

import com.applause.demo.dto.DeviceDto;
import com.applause.demo.entity.Device;

import java.util.List;

public interface DeviceService {

    List<DeviceDto> getDevices();
}
