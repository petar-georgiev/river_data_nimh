package com.example.river_data_nimh.services.impl;

import com.example.river_data_nimh.domain.DunabeBasin;
import com.example.river_data_nimh.repositories.DunabeBasinRepository;
import com.example.river_data_nimh.services.DunabeBasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DunabeBasinServiceImpl implements DunabeBasinService {

    private final DunabeBasinRepository dunabeBasinRepository;

    @Override
    public List<DunabeBasin> findByLdBetween(LocalDate startDate, LocalDate endDate) {
        return dunabeBasinRepository.findByLdBetween(startDate, endDate);
    }

    @Override
    public List<DunabeBasin> findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate) {
        return dunabeBasinRepository.findByNumStAndLdBetween(num, startDate, endDate);
    }

    @Override
    public List<DunabeBasin> findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate) {
        return dunabeBasinRepository.findByNameAndLdBetween(name, startDate, endDate);
    }

    @Override
    public List<DunabeBasin> findByLd(LocalDate date) {
        return dunabeBasinRepository.findByLd(date);
    }

    @Override
    public List<DunabeBasin> findAll() {
        return dunabeBasinRepository.findAll();
    }
}
