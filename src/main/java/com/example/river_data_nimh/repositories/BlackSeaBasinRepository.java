package com.example.river_data_nimh.repositories;

import com.example.river_data_nimh.domain.BlackSeaBasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlackSeaBasinRepository extends JpaRepository<BlackSeaBasin, Long> {
    List<BlackSeaBasin> findByLd(LocalDate date);

    List<BlackSeaBasin>findByLdBetween(LocalDate startDate, LocalDate endDate);

    List<BlackSeaBasin>findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate);
    List<BlackSeaBasin>findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate);
}
