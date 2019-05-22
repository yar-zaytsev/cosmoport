package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;

import java.util.List;

/**
 * Created by Ярпиво on 10.05.2019.
 */
public interface ShipService {
    void addShip(Ship ship);
    void updateRating(Ship ship);
    void delete(long id);
    void updateShip(Ship ship);
    Ship getByName(String name);
    Ship getById(long id);
    Ship editShip(Ship ship);
    List<Ship> getAll();

    List<Ship> getPage(int page, int limit, String order);

    /*List<Ship> getPage(String name, String planet,String shipType,long prodDate,boolean isUsed,
                            double minSpeed,double maxSpeed,int minCrewSize,int maxCrewSize,
                            int page, int limit, String order);*/

//    List<Ship> findShips(int page, int limit);
    List<Ship> findShips(String name, String planet, ShipType shipType, Long after, Long before,
                         Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                         Integer maxCrewSize, Double minRating, Double maxRating, ShipOrder order,
                         Integer page, Integer limit);

    boolean isValid(Ship ship);

    boolean isValid(Long id);

    boolean isValidForUpdate(Long id, Ship newShip);

    boolean isExistById(Long id);


}
