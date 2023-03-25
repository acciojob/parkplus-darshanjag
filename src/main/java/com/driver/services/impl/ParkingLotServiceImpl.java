package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        parkingLotRepository1.save(parkingLot);
        return parkingLot;
    }

    @Override
    public  Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        ParkingLot parking =parkingLotRepository1.findById(parkingLotId).get();
        Spot spot = new Spot();
        if(numberOfWheels==2){
           spot.setSpotType(SpotType.TWO_WHEELER);
        }else if(numberOfWheels==4){
            spot.setSpotType(SpotType.FOUR_WHEELER);
        }else if(numberOfWheels>4){
            spot.setSpotType(SpotType.OTHERS);
        }
        spot.setParkingLot(parking);
        spot.setOccupied(false);
        spot.setPricePerHour(pricePerHour);
        List<Spot> spotList=new ArrayList<>();
           spotList= parking.getSpotList();
           spotList.add(spot);
           parking.setSpotList(spotList);
           parkingLotRepository1.save(parking);
           return spot;
    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
    Spot spot  = spotRepository1.findById(spotId).get();
    spot.setPricePerHour(pricePerHour);
    spotRepository1.save(spot);

        return spot;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);

    }
    public List<Spot> getAllSpots(int parkingId){
        return parkingLotRepository1.findById(parkingId).get().getSpotList();
    }
}
