package com.example.river_data_nimh.services;

import com.example.river_data_nimh.domain.BlackSeaBasin;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface BlackSeaBasinService {
   List<BlackSeaBasin> findAll();
   List<BlackSeaBasin> findByLd(LocalDate date);
   List<BlackSeaBasin> findByLdBetween(LocalDate startDate, LocalDate endDate);
   List<BlackSeaBasin>findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate);
   List<BlackSeaBasin>findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate);
}