package com.example.river_data_nimh.services.impl;

import com.example.river_data_nimh.domain.WesternWhiteSeaBasin;
import com.example.river_data_nimh.repositories.WesternWhiteSeaBasinRepository;
import com.example.river_data_nimh.services.WesternWhiteSeaBasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WesternWhiteSeaBasinServiceImpl implements WesternWhiteSeaBasinService {

    private final WesternWhiteSeaBasinRepository westernWhiteSeaBasinRepository;

    @Override
    public List<WesternWhiteSeaBasin> findByLd(LocalDate date) {
        return westernWhiteSeaBasinRepository.findByLd(date);
    }

    @Override
    public List<WesternWhiteSeaBasin> findAll() {
        return westernWhiteSeaBasinRepository.findAll();
    }

    @Override
    public List<WesternWhiteSeaBasin> findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate) {
        return westernWhiteSeaBasinRepository.findByNameAndLdBetween(name,startDate,endDate);
    }

    @Override
    public List<WesternWhiteSeaBasin> findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate) {
        return westernWhiteSeaBasinRepository.findByNumStAndLdBetween(num,startDate,endDate);
    }

    @Override
    public List<WesternWhiteSeaBasin> findByLdBetween(LocalDate startDate, LocalDate endDate) {
        return westernWhiteSeaBasinRepository.findByLdBetween(startDate,endDate);
    }
}
