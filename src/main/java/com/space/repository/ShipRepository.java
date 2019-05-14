package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ярпиво on 10.05.2019.
 */
public interface ShipRepository extends PagingAndSortingRepository<Ship, Long> {

    @Override
    @Transactional(timeout = 10)
    List<Ship> findAll();
}
