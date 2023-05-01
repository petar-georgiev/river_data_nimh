package com.example.river_data_nimh.services.impl;

import com.example.river_data_nimh.domain.BlackSeaBasin;
import com.example.river_data_nimh.repositories.BlackSeaBasinRepository;
import com.example.river_data_nimh.services.BlackSeaBasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlackSeaBasinServiceImpl implements BlackSeaBasinService {


    private final BlackSeaBasinRepository blackSeaBasinRepository;

    @Override
    public List<BlackSeaBasin> findByLdBetween(LocalDate startDate, LocalDate endDate) {
        return blackSeaBasinRepository.findByLdBetween(startDate, endDate);
    }

    @Override
    public List<BlackSeaBasin> findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate) {
        return blackSeaBasinRepository.findByNumStAndLdBetween(num, startDate, endDate);
    }

    @Override
    public List<BlackSeaBasin> findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate) {
        return blackSeaBasinRepository.findByNameAndLdBetween(name, startDate, endDate);
    }

    @Override
    public List<BlackSeaBasin> findByLd(LocalDate date) {
        return blackSeaBasinRepository.findByLd(date);
    }

    @Override
    public List<BlackSeaBasin> findAll() {
        return blackSeaBasinRepository.findAll();
    }

}
