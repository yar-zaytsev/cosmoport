package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ShipRepository extends JpaRepository<Ship, Long>{

    @Override
    List<Ship> findAll();
}
