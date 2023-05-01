package com.example.river_data_nimh.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class WesternWhiteSeaBasin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numSt;


    private String name;


    private String station;


    private Double minQ;

    private Double avrQ;

    private Double maxQ;

    private Integer h;

    private Double q;

    private Integer dH;

    private LocalDate ld;

    @Builder
    public WesternWhiteSeaBasin(Integer numSt, String name, String station, Double minQ, Double avrQ, Double maxQ, Integer h, Double q, Integer dH, LocalDate ld){
        this.numSt = numSt;
        this.name = name;
        this.station = station;
        this.minQ = minQ;
        this.avrQ = avrQ;
        this.maxQ = maxQ;
        this.h = h;
        this.q = q;
        this.dH = dH;
        this.ld = ld;
    }
}

