package com.applause.demo;

import com.applause.demo.dto.DeviceDto;
import com.applause.demo.entity.Device;
import com.applause.demo.repository.DeviceRepository;
import com.applause.demo.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @MockBean
    private DeviceRepository deviceRepository;

    @Test
    public void shouldMapDevices() {
        List<DeviceDto> expected = asList(
                DeviceDto.builder().id(1).deviceName("DEVICE 1").build(),
                DeviceDto.builder().id(2).deviceName("DEVICE 2").build()
        );

        Mockito.when(deviceRepository.getDevices()).thenReturn(asList(
                Device.builder().id(1).description("DEVICE 1").build(),
                Device.builder().id(2).description("DEVICE 2").build()
        ));

        List<DeviceDto> result = deviceService.getDevices();

        assertThat(result).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
