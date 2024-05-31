package com.applause.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Radio radio;

    @Column
    private Integer mcc;

    @Column
    private Integer net;

    @Column
    private Integer area;

    @Column
    private Integer cell;

    @Column
    private Integer unit;

    @Column
    private Double lon;

    @Column
    private Double lat;

    @Column
    private Integer range;

    @Column
    private Integer samples;

    @Column
    private boolean changeable;

    @Column
    private Long created;

    @Column
    private Long updated;

    @Column
    private Integer averageSignal;

    public enum Radio {
        GSM, UMTS, LTE
    }
}
