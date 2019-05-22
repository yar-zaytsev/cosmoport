package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "rest/")
public class ShipController {
    @Autowired
    private ShipService shipService;

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getById(@PathVariable Long id) {
        if (!shipService.isValid(id)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!shipService.isExistById(id)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        Ship ship = shipService.getById(id);
        return new ResponseEntity<Ship>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "/ships", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createShip(@RequestBody Ship ship) {
        if (!shipService.isValid(ship)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
            shipService.addShip(ship);
            return new ResponseEntity<Ship>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable Long id) {
        if (!shipService.isValid(id)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!shipService.isExistById(id)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        shipService.delete(id);
        return new ResponseEntity<Ship>(HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateShip(@RequestBody Ship ship, @PathVariable Long id) {
        if (!shipService.isValid(id)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!shipService.isExistById(id)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        if (!shipService.isValidForUpdate(id, ship)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        shipService.updateShip(ship);
        return new ResponseEntity<Ship>(ship, HttpStatus.OK);
    }

    @RequestMapping(value = "/ships", method = RequestMethod.GET)
    @ResponseBody
    public List<Ship> getAll(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "planet", required = false) String planet,
                             @RequestParam(value = "shipType", required = false) ShipType shipType,
                             @RequestParam(value = "after", required = false) Long after,
                             @RequestParam(value = "before", required = false) Long before,
                             @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                             @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                             @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                             @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                             @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                             @RequestParam(value = "minRating", required = false) Double minRating,
                             @RequestParam(value = "maxRating", required = false) Double maxRating,
                             @RequestParam(value = "order", required = false) ShipOrder order,
                             @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                             @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {

        List<Ship> ships = shipService.findShips(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating, order, pageNumber, pageSize);
        return ships;
    }

    @RequestMapping(value = "/ships/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getCount(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "planet", required = false) String planet,
                            @RequestParam(value = "shipType", required = false) ShipType shipType,
                            @RequestParam(value = "after", required = false) Long after,
                            @RequestParam(value = "before", required = false) Long before,
                            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                            @RequestParam(value = "minRating", required = false) Double minRating,
                            @RequestParam(value = "maxRating", required = false) Double maxRating) {

        List<Ship> ships = shipService.findShips(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating, ShipOrder.ID, 0, 0);

        return ships.size();
    }

}
