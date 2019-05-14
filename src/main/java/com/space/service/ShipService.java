package com.space.service;

import com.space.model.Ship;

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
}
