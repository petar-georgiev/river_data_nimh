package com.example.river_data_nimh.services.impl;

import com.example.river_data_nimh.domain.EasternWhiteSeaBasin;
import com.example.river_data_nimh.repositories.EasternWhiteSeaBasinRepository;
import com.example.river_data_nimh.services.EasternWhiteSeaBasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EasternWhiteSeaBasinServiceImpl implements EasternWhiteSeaBasinService {

    public final EasternWhiteSeaBasinRepository easternWhiteSeaBasinRepository;

    @Override
    public List<EasternWhiteSeaBasin> findAll() {
        return easternWhiteSeaBasinRepository.findAll();
    }

    @Override
    public List<EasternWhiteSeaBasin> findByLd(LocalDate date) {
        return easternWhiteSeaBasinRepository.findByLd(date);
    }
    @Override
    public List<EasternWhiteSeaBasin> findByNameAndLdBetween(String name, LocalDate startDate, LocalDate endDate) {
        return easternWhiteSeaBasinRepository.findByNameAndLdBetween(name,startDate,endDate);
    }

    @Override
    public List<EasternWhiteSeaBasin> findByNumStAndLdBetween(Integer num, LocalDate startDate, LocalDate endDate) {
        return easternWhiteSeaBasinRepository.findByNumStAndLdBetween(num,startDate,endDate);
    }

    @Override
    public List<EasternWhiteSeaBasin> findByLdBetween(LocalDate startDate, LocalDate endDate) {
        return easternWhiteSeaBasinRepository.findByLdBetween(startDate,endDate);
    }

}
