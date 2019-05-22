package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.hibernate.query.Query;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Ярпиво on 10.05.2019.
 */
@Service
public class ShipServiceImpl implements ShipService {


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ShipRepository shipRepository;
    /*private static final Validator VALIDATOR =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();*/

    @Override
    public void addShip(Ship ship) {
//
//        Boolean isUsed = ship.isUsed();
//        double rating = getRating(ship);
//        ship.setRating(rating);
//        ship.setId(0);
//        Ship newShip = new Ship(ship.getName(),ship.getPlanet(), ship.getShipType(),ship.getProdDate(), isUsed,
//                                ship.getSpeed(), ship.getCrewSize(), rating);
        shipRepository.saveAndFlush(ship);
//        return ship;
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
//        List<Ship> returnValue = new ArrayList<>();
//        Sort.Direction direction = getDirection(order);
//        order = getFixOrder(order);
//        ExampleMatcher matcher ExampleMatcher.matching()
//                .withMatcher(targetShip.getName(), match -> match.contains().ignoreCase())
//                .withMatcher(targetShip.getPlanet(), match -> match.contains().ignoreCase())
//                .withMatcher(targetShip.getShipType().name(), match -> match.exact().ignoreCase())
//                .withMatcher(targetShip.getShipType().name(), match -> match.exact().ignoreCase())
//        ;
//        shipRepository.findByLikeNameLikePlanet


//        Pageable pageableRequest = PageRequest.of(page, limit, direction, order);


//        Page<Ship> shipPage = shipRepository.findAll(pageableRequest);
//        Page<Ship> shipPage = shipRepository.findAll(pageableRequest);

//        shipPage.
//        List<Ship> ships = shipPage.getContent();

//        return ships;
        return null;
    }

    /*@Override
    public List<Ship> getPage(String name, String planet, String shipType, long prodDate, boolean isUsed,
                                   double minSpeed, double maxSpeed, int minCrewSize, int maxCrewSize,
                                   int page, int limit, String order) {

        Sort.Direction direction = getDirection(order);
        Pageable pageableRequest = PageRequest.of(page, limit, direction, order);

        List<Ship> ships  = shipRepository.getPageShips(name,planet,shipType,prodDate,isUsed,minSpeed,
                maxSpeed,minCrewSize,maxCrewSize,page,limit,order,direction.name());
//        List<Ship> ships = shipPage.getContent();

        return ships;
    }*/

   /* @Override
    public List findShips(int page, int limit) {
       *//* LocalDate today = new LocalDate();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ship> query = builder.createQuery(Ship.class);
        Root<Ship> root = query.from(Ship.class);

        Predicate likeName = builder.like(root.get(Ship_.birthday), today);
        Predicate isLongTermCustomer = builder.lessThan(root.get(Customer_.createdAt), today.minusYears(2);
        query.where(builder.and(hasBirthday, isLongTermCustomer));*//*
//       return entityManager.createQuery(query.select(root)).getResultList();
       return null;
    }*/

    public List<Ship> findShips(String name, String planet, ShipType shipType, Long after, Long before,
                                Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                Integer maxCrewSize, Double minRating, Double maxRating, String order,
                                Integer page, Integer limit) {
/*boolean where = false;
        StringBuilder qsb = new StringBuilder("SELECT ship from Ship ship ");
        if(name != null) qsb.append(qsb.toString().contains("where") ? " " + name : "where " + name);
        if(name != null) qsb.append(qsb.toString().contains("where") ? " " + planet : "where " + planet);
        if(name != null) qsb.append(qsb.toString().contains("where") ? " " + shipType : "where " + shipType);
        if(name != null) qsb.append(qsb.toString().contains("where") ? " " + after : "where " + after);
        if(name != null) qsb.append(qsb.toString().contains("where") ? " " + before : "where " + before);
        if(name != null) qsb.append(qsb.toString().contains("where") ? " is " + isUsed : "where " + isUsed);
        if(name != null) qsb.append(qsb.toString().contains("where") ? " " + after : "where " + after);
        if(name != null) qsb.append(qsb.toString().contains("where") ? " " + after : "where " + after);



//*//*ship.shipType = :shipType and*//*
        List<?> movies = entityManager.createQuery("SELECT ship from Ship ship where" +
                " ship.name like :name and ship.planet like :planet and " +
                " ship.prodDate > :after and  ship.prodDate < :before and ship.isUsed = :isUsed and "+
                " ship.speed between :minSpeed and :maxSpeed and" +
                " ship.crewSize between :minCrewSize and :maxCrewSize and " +
                " ship.rating between :minRating and  :maxRating order by :ordr")
                .setParameter("name", name)
                .setParameter("planet", planet)
                //.setParameter("shipType", shipType)
                .setParameter("after", after)
                .setParameter("before", before)
                .setParameter("isUsed", isUsed)
                .setParameter("minSpeed", minSpeed)
                .setParameter("maxSpeed", maxSpeed)
                .setParameter("minCrewSize", minCrewSize)
                .setParameter("maxCrewSize", maxCrewSize)
                .setParameter("minRating", minRating)
                .setParameter("maxRating", maxRating)
                .setParameter("ordr", order)
                .setFirstResult((page-1)*limit)
                .setMaxResults(limit)
                .getResultList();
        return movies;*/

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ship> cr = cb.createQuery(Ship.class);
        Root<Ship> root = cr.from(Ship.class);
//        cr.select(root);
        ArrayList<Predicate> predicates = new ArrayList<>();
        if (name != null) predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        if (planet != null) predicates.add(cb.like(root.get("planet"), "%" + planet + "%"));

        if (shipType != null) predicates.add(cb.equal(root.<ShipType>get("shipType"), shipType));

        if (after != null) predicates.add(cb.greaterThanOrEqualTo(root.get("prodDate"), new Date(after)));
        if (before != null) predicates.add(cb.lessThanOrEqualTo(root.get("prodDate"), new Date(before)));

        if (isUsed != null) predicates.add(cb.equal(root.<Boolean>get("isUsed"), isUsed));
//       predicates.add(cb.like(root.get("isUsed"), isUsed +""));

        if (minSpeed != null) predicates.add(cb.greaterThanOrEqualTo(root.get("speed"), minSpeed));
        if (maxSpeed != null) predicates.add(cb.lessThanOrEqualTo(root.get("speed"), maxSpeed));

        if (minCrewSize != null) predicates.add(cb.greaterThanOrEqualTo(root.get("crewSize"), minCrewSize));
        if (maxCrewSize != null) predicates.add(cb.lessThanOrEqualTo(root.get("crewSize"), maxCrewSize));

        if (minRating != null) predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), minRating));
        if (maxRating != null) predicates.add(cb.lessThanOrEqualTo(root.get("rating"), maxRating));

        if (maxSpeed != null) predicates.add(cb.lessThanOrEqualTo(root.get("speed"), maxSpeed));


        Predicate[] predicatesArr = predicates.toArray(new Predicate[predicates.size()]);
//        predicates[0] = cb.isNull(root.get("itemDescription"));
//        predicates[1] = cb.like(root.get("itemName"), "chair%");
        cr.select(root).where(predicatesArr);

        order = getFixOrder(order);
        cr.orderBy(cb.asc(root.get(order)));

        /*if (order.equals("id")) {

            cr.orderBy(cb.asc(root.get("id")));
        }else {
            cr.orderBy(cb.desc(root.get(order)));

        }*/

       /* if (page > 0 && limit > 0) {
            page = (page - 1) * limit;
        }*/
//        Query<Ship> query = entityManager.createQuery(cr);
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

        /*Set<ConstraintViolation<Ship>> violations = VALIDATOR.validate(ship);
        if (violations.size() > 0) isValid = false;*/
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
            ship.setId(0);
        }
        return isValid;
    }


    private String getFixOrder(String order) {
        if (order.equals("DATE")) {
            order = "prodDate";
        } else {
            order = order.toLowerCase();

        }
        return order;
    }

    /*private Sort.Direction getDirection(String order) {
        Sort.Direction direction;
        if (order.equals("ID")) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }

        return direction;
    }*/
}
