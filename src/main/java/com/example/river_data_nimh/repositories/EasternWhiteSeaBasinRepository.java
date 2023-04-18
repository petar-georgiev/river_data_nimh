package com.example.river_data_nimh.repositories;

import com.example.river_data_nimh.domain.DunabeBasin;
import com.example.river_data_nimh.domain.EasternWhiteSeaBasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EasternWhiteSeaBasinRepository extends JpaRepository<EasternWhiteSeaBasin, Long> {

}
