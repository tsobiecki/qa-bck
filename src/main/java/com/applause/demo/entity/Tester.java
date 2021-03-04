package com.applause.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String country;

    @Column
    private Date lastLogin;

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name="tester_testerId")
    private List<Device> devices;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="bug_bugId")
    private List<Bug> bugs;
}
