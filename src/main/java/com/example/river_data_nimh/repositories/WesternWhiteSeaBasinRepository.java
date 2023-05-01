package com.example.river_data_nimh.repositories;

import com.example.river_data_nimh.domain.WesternWhiteSeaBasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WesternWhiteSeaBasinRepository extends JpaRepository<WesternWhiteSeaBasin, Long> {

    List<WesternWhiteSeaBasin> findByLd(LocalDate date);

    List<WesternWhiteSeaBasin>findByLdBetween(LocalDate startDate, LocalDate endDate);

    List<WesternWhiteSeaBasin>findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate);
    List<WesternWhiteSeaBasin>findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate);

}
