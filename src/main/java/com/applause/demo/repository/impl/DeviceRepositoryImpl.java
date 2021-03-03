package com.applause.demo.repository.impl;

import com.applause.demo.entity.Device;
import com.applause.demo.repository.DeviceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    @Override
    public List<Device> getDevices() {
        return null;
    }

}
