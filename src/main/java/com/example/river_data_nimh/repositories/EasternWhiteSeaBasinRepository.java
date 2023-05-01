package com.example.river_data_nimh.repositories;

import com.example.river_data_nimh.domain.EasternWhiteSeaBasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EasternWhiteSeaBasinRepository extends JpaRepository<EasternWhiteSeaBasin, Long> {
    List<EasternWhiteSeaBasin> findByLd(LocalDate date);
    List<EasternWhiteSeaBasin>findByLdBetween(LocalDate startDate, LocalDate endDate);

    List<EasternWhiteSeaBasin>findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate);
    List<EasternWhiteSeaBasin>findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate);

}
