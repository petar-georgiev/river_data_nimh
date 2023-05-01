package com.example.river_data_nimh.services;

import com.example.river_data_nimh.domain.DunabeBasin;

import java.time.LocalDate;
import java.util.List;

public interface DunabeBasinService {
    List<DunabeBasin>findByLd(LocalDate date);
    List<DunabeBasin> findAll();
    List<DunabeBasin> findByLdBetween(LocalDate startDate, LocalDate endDate);
    List<DunabeBasin>findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate);
    List<DunabeBasin>findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate);
}
