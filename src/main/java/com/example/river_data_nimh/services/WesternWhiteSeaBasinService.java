package com.example.river_data_nimh.services;

import com.example.river_data_nimh.domain.WesternWhiteSeaBasin;

import java.time.LocalDate;
import java.util.List;

public interface WesternWhiteSeaBasinService {
    List<WesternWhiteSeaBasin>findByLd(LocalDate date);
    List<WesternWhiteSeaBasin>findAll();

    List<WesternWhiteSeaBasin> findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate);

    List<WesternWhiteSeaBasin> findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate);

    List<WesternWhiteSeaBasin> findByLdBetween(LocalDate startDate, LocalDate endDate);

}
