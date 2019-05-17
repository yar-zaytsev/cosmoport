package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ярпиво on 10.05.2019.
 */
public interface ShipRepository extends PagingAndSortingRepository<Ship, Long>, JpaSpecificationExecutor {



    @Override
    @Transactional(timeout = 10)
    List<Ship> findAll();

    /*@Query("from Ships s where s.name like %:name% and s.planet like %:planet%" +
            " and s.shipType =:shipType and s.prodDate > :after and s.prodDate < :before" +
            " and s.isUsed = :isUsed and s.speed >= :minSpeed and s.speed <= :maxSpeed" +
            " and s.crewSize >= :minCrewSize and s.crewSize <= :maxCrewSize" +
            " and s.rating >= :minRating and s.rating <= :maxRating")
    List<Ship> getPageShips(@Param("name") String name,
                            @Param("planet") String planet,
                            @Param("shipType") String shipType,
                            @Param("prodDate") long prodDate,
                            @Param("isUsed") boolean isUsed,
                            @Param("minSpeed") double minSpeed,
                            @Param("maxSpeed") double maxSpeed,
                            @Param("minCrewSize") int minCrewSize,
                            @Param("maxCrewSize") int maxCrewSize,
                            @Param("minRating")double minRating,
                            @Param("maxRating")double maxRating,
                            @Param("byOrder") String byOrder,
                            @Param("direction") String direction);*/




}
