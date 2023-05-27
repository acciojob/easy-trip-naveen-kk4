package com.driver.controllers;


import com.driver.AirportService;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class AirportController {
    @Autowired
    AirportService service;
    @PostMapping("/add_airport")
    public String addAirport(@RequestBody Airport airport){

      service.addAirport(airport);

        return "SUCCESS";
    }

    @GetMapping("/get-largest-aiport")
    public String getLargestAirportName(){

       return service.getLargestAirportName();
    }

    @GetMapping("/get-shortest-time-travel-between-cities")
    public double getShortestDurationOfPossibleBetweenTwoCities(@RequestParam("fromCity") City fromCity, @RequestParam("toCity")City toCity){
        return service.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);

    }

    @GetMapping("/get-number-of-people-on-airport-on/{date}")
    public int getNumberOfPeopleOn(@PathVariable("date") Date date,@RequestParam("airportName")String airportName){
        return service.getNumberOfPeopleOn(date,airportName);


    }

    @GetMapping("/calculate-fare")
    public int calculateFlightFare(@RequestParam("flightId")Integer flightId){
        return service.calculateFlightFare(flightId);

    }


    @PostMapping("/book-a-ticket")
    public String bookATicket(@RequestParam("flightId")Integer flightId,@RequestParam("passengerId")Integer passengerId){

        return service.bookATicket(flightId,passengerId);
    }

    @PutMapping("/cancel-a-ticket")
    public String cancelATicket(@RequestParam("flightId")Integer flightId,@RequestParam("passengerId")Integer passengerId){

        return service.cancelATicket(flightId,passengerId);
    }


    @GetMapping("/get-count-of-bookings-done-by-a-passenger/{passengerId}")
    public int countOfBookingsDoneByPassengerAllCombined(@PathVariable("passengerId")Integer passengerId){


       return service.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    @PostMapping("/add-flight")
    public String addFlight(@RequestBody Flight flight){

       service.addFlight(flight);
       return "SUCCESS";
    }


    @GetMapping("/get-aiportName-from-flight-takeoff/{flightId}")
    public String getAirportNameFromFlightId(@PathVariable("flightId")Integer flightId){

       return service.getAirportNameFromFlightId(flightId);


    }


    @GetMapping("/calculate-revenue-collected/{flightId}")
    public int calculateRevenueOfAFlight(@PathVariable("flightId")Integer flightId){

       return service.calculateRevenueOfAFlight(flightId);

    }


    @PostMapping("/add-passenger")
    public String addPassenger(@RequestBody Passenger passenger){
        service.addPassenger(passenger);
       return "SUCCESS";
    }


}
