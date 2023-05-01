package com.example.river_data_nimh.services;

import com.example.river_data_nimh.domain.EasternWhiteSeaBasin;

import java.time.LocalDate;
import java.util.List;

public interface EasternWhiteSeaBasinService {
    List<EasternWhiteSeaBasin>findByLd(LocalDate date);
    List<EasternWhiteSeaBasin> findAll();

    List<EasternWhiteSeaBasin> findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate);

    List<EasternWhiteSeaBasin> findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate);

    List<EasternWhiteSeaBasin> findByLdBetween(LocalDate startDate, LocalDate endDate);

}
