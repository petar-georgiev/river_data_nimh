package com.example.river_data_nimh.repositories;

import com.example.river_data_nimh.domain.DunabeBasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DunabeBasinRepository extends JpaRepository<DunabeBasin, Long> {
    List<DunabeBasin> findByLd(LocalDate date);

    List<DunabeBasin>findByLdBetween(LocalDate startDate, LocalDate endDate);

    List<DunabeBasin>findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate);
    List<DunabeBasin>findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate);
}
