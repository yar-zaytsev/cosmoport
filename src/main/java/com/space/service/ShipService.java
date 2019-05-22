package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;

import java.util.List;

public interface ShipService {
    void addShip(Ship ship);
    void updateRating(Ship ship);
    void delete(Long id);
    void updateShip(Ship ship);
    Ship getById(long id);
    List<Ship> getAll();
    List<Ship> findShips(String name, String planet, ShipType shipType, Long after, Long before,
                         Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                         Integer maxCrewSize, Double minRating, Double maxRating, ShipOrder order,
                         Integer page, Integer limit);

    boolean isValid(Ship ship);

    boolean isValid(Long id);

    boolean isValidForUpdate(Long id, Ship newShip);

    boolean isExistById(Long id);


}
