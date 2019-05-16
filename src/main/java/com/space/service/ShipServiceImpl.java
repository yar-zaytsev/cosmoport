package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ярпиво on 10.05.2019.
 */
@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private EntityManager entityManager;

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
//        order = getFixOrder(order);
//        ExampleMatcher matcher ExampleMatcher.matching()
//                .withMatcher(targetShip.getName(), match -> match.contains().ignoreCase())
//                .withMatcher(targetShip.getPlanet(), match -> match.contains().ignoreCase())
//                .withMatcher(targetShip.getShipType().name(), match -> match.exact().ignoreCase())
//                .withMatcher(targetShip.getShipType().name(), match -> match.exact().ignoreCase())
//        ;
//        shipRepository.findByLikeNameLikePlanet


        Pageable pageableRequest = PageRequest.of(page, limit, direction, order);


        Page<Ship> shipPage = shipRepository.findAll(pageableRequest);
//        Page<Ship> shipPage = shipRepository.findAll(pageableRequest);

//        shipPage.
        List<Ship> ships = shipPage.getContent();

        return ships;
    }

    @Override
    public List<Ship> getPage(String name, String planet, String shipType, long prodDate, boolean isUsed,
                                   double minSpeed, double maxSpeed, int minCrewSize, int maxCrewSize,
                                   int page, int limit, String order) {

        Sort.Direction direction = getDirection(order);
        Pageable pageableRequest = PageRequest.of(page, limit, direction, order);

        List<Ship> ships  = shipRepository.getPageShips(name,planet,shipType,prodDate,isUsed,minSpeed,
                maxSpeed,minCrewSize,maxCrewSize,page,limit,order,direction.name());
//        List<Ship> ships = shipPage.getContent();

        return ships;
    }

    @Override
    public List findShips(int page, int limit) {
        LocalDate today = new LocalDate();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ship> query = builder.createQuery(Ship.class);
        Root<Ship> root = query.from(Ship.class);

        Predicate likeName = builder.like(root.get(Ship_.birthday), today);
        Predicate isLongTermCustomer = builder.lessThan(root.get(Customer_.createdAt), today.minusYears(2);
        query.where(builder.and(hasBirthday, isLongTermCustomer));
        em.createQuery(query.select(root)).getResultList();
    }

    private String getFixOrder(String order) {
        if (order.equals("DATE")) {
            order = "prodDate";
        } else {
            order = order.toLowerCase();

        }
        return order;
    }

    private Sort.Direction getDirection(String order) {
        Sort.Direction direction;
        if (order.equals("ID")) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }

        return direction;
    }
}
