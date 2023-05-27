package com.driver;

import com.driver.model.Airport;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository

public class AirportRepository {
   List<Airport> airports = new ArrayList<>();
    Map<Integer, List<Integer>> flightsList = new HashMap<>();
    Map<Integer,List<Integer>> passengersList= new HashMap<>();
    Map<Integer,Flight> flights = new HashMap<>();
    Map<Integer,Passenger> passengers = new HashMap<>();

    public void addAirport(Airport airport) {
        airports.add(airport);
    }

    public List<Airport> getListOfAllAirports() {
        return airports;
    }

    public void addFlight(Flight flight) {
        flightsList.put(flight.getFlightId(),new ArrayList<>());
        flights.put(flight.getFlightId(),flight);
    }

    public void addPassenger(Passenger passenger) {
        passengersList.put(passenger.getPassengerId(),new ArrayList<>());
        passengers.put(passenger.getPassengerId(),passenger);
    }

    public List<Flight> getAllFlights() {
        return new ArrayList<>(flights.values());
    }

    public int getNumOfPassengersAlreadyInTheFlight(Integer flightId) {
        return flightsList.get(flightId).size();
    }

    public Flight getFlight(Integer flightId) {
        return flights.containsKey(flightId)?flights.get(flightId):null;
    }

    public List<Integer> listOfAllPassengersAlreadyInTheFlight(Integer flightId) {
        return flightsList.get(flightId);
    }

    public void addFlightPassenger(Integer flightId, Integer passengerId) {
        flightsList.get(flightId).add(passengerId);
        passengersList.get(passengerId).add(flightId);
    }

    public Passenger getPassenger(Integer passengerId) {
        return passengers.containsKey(passengerId)?passengers.get(passengerId):null;
    }

    public void deleteFlightPassenger(Integer flightId, Integer passengerId) {
        flightsList.get(flightId).remove(passengerId);
        passengersList.get(passengerId).remove(flightId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        return passengersList.get(passengerId).size();
    }
}
