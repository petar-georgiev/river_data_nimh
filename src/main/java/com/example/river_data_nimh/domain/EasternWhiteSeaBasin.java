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
public class EasternWhiteSeaBasin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer a_numSt;


    private String b_name;


    private String c_station;


    private Double d_minQ;

    private Double e_avrQ;


    private Double f_maxQ;

    private Integer g_h;

    private Double h_q;

    private Integer i_dH;

    private LocalDate j_ld;

    @Builder
    public EasternWhiteSeaBasin(Integer a_numSt, String b_name, String c_station, Double d_minQ, Double e_avrQ, Double f_maxQ, Integer g_h, Double h_q, Integer i_dH, LocalDate j_ld){
        this.a_numSt = a_numSt;
        this.b_name = b_name;
        this.c_station = c_station;
        this.d_minQ = d_minQ;
        this.e_avrQ = e_avrQ;
        this.f_maxQ = f_maxQ;
        this.g_h = g_h;
        this.h_q = h_q;
        this.i_dH = i_dH;
        this.j_ld = j_ld;
    }
}

