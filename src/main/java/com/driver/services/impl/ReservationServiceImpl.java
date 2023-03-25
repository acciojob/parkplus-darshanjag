package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {


        Reservation  reservation = new Reservation();
        User user;
        try{
           user = userRepository3.findById(userId).get();
        }catch (Exception e){
            throw new Exception("the user is not found");
        }

        List<Reservation>userRes = new ArrayList<>();
        userRes = user.getReservationList();
        ParkingLot parking;
        try{
            parking=parkingLotRepository3.findById(parkingLotId).get();
        }catch (Exception e){
            throw new Exception("parking lot is not found");
        }

        List<Spot> spotList = parking.getSpotList();


        List<Spot> validSpots = new ArrayList<>();
        for(Spot spot: spotList){
           if(!spot.isOccupied()){

               if(numberOfWheels<=2&&(spot.getSpotType()==SpotType.OTHERS  || spot.getSpotType()==SpotType.FOUR_WHEELER|| spot.getSpotType()
               ==SpotType.TWO_WHEELER)){

                   validSpots.add(spot);
               }else if(numberOfWheels<=4&&numberOfWheels>2&&(spot.getSpotType()==SpotType.FOUR_WHEELER||spot.getSpotType()==SpotType.OTHERS)){
                   validSpots.add(spot);
               }else if(numberOfWheels>4&&spot.getSpotType()==SpotType.OTHERS){
                   validSpots.add(spot);
               }
           }
        }
        System.out.println(validSpots);
        if(validSpots.size()==0){
            throw new Exception("no spot is available");
        }
        Spot spot1 = null;
        for(Spot spot2 : validSpots){
            if(spot1==null|| spot2.getPricePerHour()<spot1.getPricePerHour()){
                spot1=spot2;
            }
        }

        spot1.setOccupied(true);
        reservation.setSpot(spot1);
        reservation.setNumberOfHours(timeInHours);
        reservation.setUser(user);
        userRes.add(reservation);
        user.setReservationList(userRes);
        userRepository3.save(user);
        return reservation;
    }
}
