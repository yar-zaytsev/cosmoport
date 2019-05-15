package com.space.service;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ярпиво on 10.05.2019.
 */
public interface ShipService {
    Ship addShip(Ship ship);
    void delete(long id);
    Ship getByName(String name);
    Ship getById(long id);
    Ship editShip(Ship ship);
    List<Ship> getAll();

    List<Ship> getPage(int page, int limit, String order);
    @Query("from Ships s where s.name like %:name% and s.planet like %:planet%" +
            " and s.shipType =:shiptype and s.prodDate > :after and s.prodDate < :before" +
            " and s.isUsed = :isUsed and s.speed >= :minSpeed and s.speed <= :maxSpeed" +
            " and s.rating >= :minRating and s.rating <= :maxRating ")
    List<Ship> getPageShips(@Param("name") String name, String planet,@Param("planet") String planet,
                            @Param("shiptype") String shiptype, @Param("prodDate") long prodDate,
                            @Param("isUsed") boolean isUsed, @Param("minSpeed") double minSpeed,
                            @Param("name") String name,@Param("name") String name,@Param("name") String name,
                            @Param("name") String name,@Param("name") String name,int page, int limit, String order);
}
