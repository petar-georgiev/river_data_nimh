package com.example.river_data_nimh.repositories;

import com.example.river_data_nimh.domain.DunabeBasin;
import com.example.river_data_nimh.domain.WesternWhiteSeaBasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WesternWhiteSeaBasinRepository extends JpaRepository<WesternWhiteSeaBasin, Long> {

}
