package com.applause.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String description;

    @OneToMany
    @JoinColumn(name="device_deviceId")
    private List<Tester> testers;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="device_deviceId")
    private List<Bug> bugs;

}
