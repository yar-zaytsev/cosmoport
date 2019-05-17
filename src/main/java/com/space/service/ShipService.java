package com.space.service;

import com.space.model.Ship;
import java.util.Calendar;
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

    /*List<Ship> getPage(String name, String planet,String shipType,long prodDate,boolean isUsed,
                            double minSpeed,double maxSpeed,int minCrewSize,int maxCrewSize,
                            int page, int limit, String order);*/

//    List<Ship> findShips(int page, int limit);
    List<?> findShips(String name, String planet, String shipType, String after, String before,
                      String isUsed, String minSpeed, String maxSpeed, String minCrewSize,
                      String maxCrewSize, String minRating, String maxRating, String order,
                      int page, int limit);
}
