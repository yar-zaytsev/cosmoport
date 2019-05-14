package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ярпиво on 10.05.2019.
 */
@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public Ship addShip(Ship ship) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Ship getByName(String name) {
        return null;
    }

    @Override
    public Ship getById(long id) {
        return shipRepository.findById(id).get();
    }

    @Override
    public Ship editShip(Ship ship) {
        return null;
    }

    @Override
    public List<Ship> getAll() {
        return shipRepository.findAll();
    }

    @Override
    public List<Ship> getPage(int page, int limit, String order) {
        List<Ship> returnValue = new ArrayList<>();
        Sort.Direction direction = getDirection(order);
        order = getFixOrder(order);


        Pageable pageableRequest = PageRequest.of(page, limit, direction, order);
        Page<Ship> shipPage = shipRepository.findAll(pageableRequest);
        List<Ship> ships = shipPage.getContent();

        return ships;
    }

    private String getFixOrder(String order) {
        if (order.equals("DATE")) {
            order = "prodDate";
        }else {
            order = order.toLowerCase();

        }
        return order;
    }

    private Sort.Direction getDirection(String order) {
        Sort.Direction direction ;
        if (order.equals("ID")) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }

        return direction;
    }
}
