package com.driver.controllers;

import com.driver.services.ParkingLotService;
import com.driver.services.impl.ParkingLotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.driver.model.*;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    //findById and deleteById should be used wherever necessary
    //findAll should never be used
    @Autowired
    ParkingLotServiceImpl parkingLotService;

    @PostMapping("/add")
    public ResponseEntity<ParkingLot> addParkingLot(@RequestParam String name, @RequestParam String address) {
        ParkingLot newParkingLot = parkingLotService.addParkingLot(name,address);
        return new ResponseEntity<>(newParkingLot, HttpStatus.CREATED);
    }

    @PostMapping("/{parkingLotId}/spot/add")
    public ResponseEntity<Spot> addSpot(@PathVariable int parkingLotId, @RequestParam Integer numberOfWheels, @RequestParam Integer pricePerHour) {
        //create a new spot in the parkingLot with given id
        //the spot type should be the next biggest type in case the number of wheels are not 2 or 4, for 4+ wheels, it is others
        Spot newSpot = parkingLotService.addSpot(parkingLotId,numberOfWheels,pricePerHour);
        return new ResponseEntity<>(newSpot,HttpStatus.CREATED);
    }

    @DeleteMapping("/spot/{spotId}/delete")
    public ResponseEntity<Void> deleteSpot(@PathVariable int spotId) {
        parkingLotService.deleteSpot(spotId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{parkingLotId}/spot/{spotId}/update")
    public ResponseEntity<Spot> updateSpot(@PathVariable int parkingLotId, @PathVariable int spotId, @RequestParam int pricePerHour) {

        Spot spot =parkingLotService.updateSpot(parkingLotId,spotId,pricePerHour);
        return new ResponseEntity<>(spot,HttpStatus.OK);
    }

    @DeleteMapping("/{parkingLotId}/delete")
    public ResponseEntity<Void> deleteParkingLot(@PathVariable int parkingLotId) {
        parkingLotService.deleteParkingLot(parkingLotId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{parkingId}/get_spots")
    public ResponseEntity<List<Spot>> getALLSpots(@PathVariable int parkingId){
        List<Spot> spots=parkingLotService.getAllSpots(parkingId);
        return new ResponseEntity<>(spots,HttpStatus.OK);
    }

}
