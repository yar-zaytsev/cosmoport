package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public void addShip(Ship ship) {
        ship.setId(0);
        updateRating(ship);
        shipRepository.save(ship);
    }

    public void updateRating(Ship ship) {

        if (ship.isUsed() != null && ship.getProdDate() != null && ship.getSpeed() != null) {
            double k = ship.isUsed() ? 0.5 : 1;
            int prodDateYear = ship.getProdDate().getYear() + 1900;
            double rating = (80 * ship.getSpeed() * k) / (3019 - prodDateYear + 1);
            rating = new BigDecimal(rating).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            ship.setRating(rating);
        }
    }

    @Override
    public Ship getById(long id) {
        return shipRepository.findById(id).get();
    }

    @Override
    public List<Ship> getAll() {
        return shipRepository.findAll();
    }

    public List<Ship> findShips(String name, String planet, ShipType shipType, Long after, Long before,
                                Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                Integer maxCrewSize, Double minRating, Double maxRating, ShipOrder order,
                                Integer page, Integer limit) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ship> cr = cb.createQuery(Ship.class);
        Root<Ship> root = cr.from(Ship.class);
        ArrayList<Predicate> predicates = new ArrayList<>();
        if (name != null) predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        if (planet != null) predicates.add(cb.like(root.get("planet"), "%" + planet + "%"));
        if (shipType != null) predicates.add(cb.equal(root.<ShipType>get("shipType"), shipType));
        if (after != null) predicates.add(cb.greaterThanOrEqualTo(root.get("prodDate"), new Date(after)));
        if (before != null) predicates.add(cb.lessThanOrEqualTo(root.get("prodDate"), new Date(before)));
        if (isUsed != null) predicates.add(cb.equal(root.<Boolean>get("isUsed"), isUsed));
        if (minSpeed != null) predicates.add(cb.greaterThanOrEqualTo(root.get("speed"), minSpeed));
        if (maxSpeed != null) predicates.add(cb.lessThanOrEqualTo(root.get("speed"), maxSpeed));
        if (minCrewSize != null) predicates.add(cb.greaterThanOrEqualTo(root.get("crewSize"), minCrewSize));
        if (maxCrewSize != null) predicates.add(cb.lessThanOrEqualTo(root.get("crewSize"), maxCrewSize));
        if (minRating != null) predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), minRating));
        if (maxRating != null) predicates.add(cb.lessThanOrEqualTo(root.get("rating"), maxRating));
        if (maxSpeed != null) predicates.add(cb.lessThanOrEqualTo(root.get("speed"), maxSpeed));

        Predicate[] predicatesArr = predicates.toArray(new Predicate[predicates.size()]);
        cr.select(root).where(predicatesArr);
       String orderString = getFixOrder(order);
        cr.orderBy(cb.asc(root.get(orderString)));

        TypedQuery<Ship> query = entityManager.createQuery(cr);

        if (limit != 0) {
            page = page * limit;
            query.setFirstResult(page);
            query.setMaxResults(limit);
        }

        List<Ship> results = query.getResultList();

        return results;


    }

    @Override
    public boolean isValid(Ship ship) {
        boolean isValid = true;
        if (ship.getName() == null) {
            isValid = false;
        } else {
            if (ship.getName().isEmpty()) isValid = false;
            if (ship.getName().length() > 50) isValid = false;
        }
        if (ship.getPlanet() == null) {
            isValid = false;
        } else {
            if (ship.getPlanet().isEmpty()) isValid = false;
            if (ship.getPlanet().length() > 50) isValid = false;
        }
        if (ship.getShipType() == null) {
            isValid = false;
        } else {
            if (!Arrays.asList(ShipType.values()).contains(ship.getShipType())) isValid = false;
        }
        if (ship.getSpeed() == null) {
            isValid = false;
        } else {
            if (0.01d > ship.getSpeed() || ship.getSpeed() > 0.99d) isValid = false;
        }
        if (ship.getCrewSize() == null) {
            isValid = false;
        } else {
            if (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999) isValid = false;
        }
        if (ship.getProdDate() == null) isValid = false;
        if (isValid) {
            long prodDate = ship.getProdDate().getTime();
            if (prodDate < 0) isValid = false;
            int prodYear = ship.getProdDate().getYear() + 1900;
            if (prodYear < 2800) isValid = false;
            if (prodYear > 3019) isValid = false;
            if (ship.isUsed() == null) ship.setUsed(false);
        }
        return isValid;
    }

    @Override
    public boolean isValid(Long id) {
        boolean isValid = false;
        try {
            if (id > 0) isValid =  true;
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public boolean isValidForUpdate(Long id, Ship newShip) {
        boolean isValid = false;
        Optional<Ship> optionalShip =  shipRepository.findById(id);
        if (optionalShip.isPresent()) {
            Ship oldShip = optionalShip.get();
            newShip.setId(id);
            if(newShip.getName() == null) newShip.setName(oldShip.getName());
            if(newShip.getPlanet() == null) newShip.setPlanet(oldShip.getPlanet());
            if(newShip.getShipType() == null) newShip.setShipType(oldShip.getShipType());
            if(newShip.getProdDate() == null) newShip.setProdDate(oldShip.getProdDate());
            if(newShip.isUsed() == null) newShip.setUsed(oldShip.isUsed());
            if(newShip.getSpeed() == null) newShip.setSpeed(oldShip.getSpeed());
            if(newShip.getCrewSize() == null) newShip.setCrewSize(oldShip.getCrewSize());
            if(isValid(newShip)) isValid = true;
        }
        return isValid;
    }

    @Override
    public void delete(Long id) {
        shipRepository.deleteById(id);
    }

    @Override
    public void updateShip(Ship ship) {
        updateRating(ship);
        shipRepository.save(ship);
    }

    @Override
    public boolean isExistById(Long id) {
        return shipRepository.existsById(id);
    }

    private String getFixOrder(ShipOrder order) {
        if (order == null) return "id";
        return order.name().toLowerCase();
    }
}
