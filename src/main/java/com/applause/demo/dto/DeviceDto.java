package com.applause.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceDto {

    private Integer id;
    private String deviceName;
}
