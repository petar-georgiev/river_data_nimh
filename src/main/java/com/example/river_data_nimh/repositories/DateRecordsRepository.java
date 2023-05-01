package com.example.river_data_nimh.repositories;

import com.example.river_data_nimh.domain.DateRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DateRecordsRepository extends JpaRepository<DateRecords, Long> {
    DateRecords findTopByOrderByDateDesc();
}
