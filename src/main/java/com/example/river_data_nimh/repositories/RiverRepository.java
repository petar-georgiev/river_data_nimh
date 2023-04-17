package com.example.river_data_nimh.repositories;

import com.example.river_data_nimh.domain.River;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiverRepository extends JpaRepository<River, Long> {

}
