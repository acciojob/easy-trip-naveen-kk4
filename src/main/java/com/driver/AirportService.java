package com.driver;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class AirportService {

    AirportRepository dao = new AirportRepository();

    public void addAirport(Airport airport) {
        dao.addAirport(airport);
    }

    public String getLargestAirportName() {
        List<Airport> list = dao.getListOfAllAirports();
        String largest = "";
        int max = 0;
        for(Airport air : list){
            if(air.getNoOfTerminals() > max ){
                max = air.getNoOfTerminals();
                largest = air.getAirportName();
            }
            else if(air.getNoOfTerminals() == max && air.getAirportName().compareTo(largest)<0){
                largest = air.getAirportName();
            }
        }
        return largest==""?null:largest;
    }

    public void addFlight(Flight flight) {
        dao.addFlight(flight);
    }

    public void addPassenger(Passenger passenger) {
        dao.addPassenger(passenger);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        List<Flight> list = dao.getAllFlights();
        double duration = Double.MAX_VALUE;
        for(Flight flight : list){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                duration = Math.min(duration, flight.getDuration());
            }
        }
        return duration==Double.MAX_VALUE?-1:duration;
    }

    public int calculateFlightFare(Integer flightId) {
        int size = dao.getNumOfPassengersAlreadyInTheFlight(flightId);
        return 3000+(50*size);
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        Flight curr = dao.getFlight(flightId);
        Passenger pa = dao.getPassenger(passengerId);
        if(curr==null || pa==null)return "FAILURE";
        int size = dao.getNumOfPassengersAlreadyInTheFlight(flightId);
        if(size== curr.getMaxCapacity())return "FAILURE";
        List<Integer> list = dao.listOfAllPassengersAlreadyInTheFlight(flightId);
        if(list.contains(passengerId))return "FAILURE";
        dao.addFlightPassenger(flightId,passengerId);
        return "SUCCESS";

    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        Flight curr = dao.getFlight(flightId);
        Passenger pa = dao.getPassenger(passengerId);
        if(curr==null || pa==null)return "FAILURE";
        List<Integer> list = dao.listOfAllPassengersAlreadyInTheFlight(flightId);
        if(!list.contains(passengerId))return "FAILURE";
        dao.deleteFlightPassenger(flightId,passengerId);
        return "SUCCESS";
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        return dao.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        int count = dao.getNumOfPassengersAlreadyInTheFlight(flightId);
        int ans = 0;
        int num = 0;
        for(int i = 0;i<count;i++){
            ans+=3000+num;
            num+=50;
        }
        return ans;

    }

    public String getAirportNameFromFlightId(Integer flightId) {
        Flight curr = dao.getFlight(flightId);
        if(curr==null)return null;
        List<Airport> list = dao.getListOfAllAirports();
        for(Airport air : list){
            if(air.getCity().equals(curr.getFromCity()))return air.getCity().toString();
        }
        return null;
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        List<Airport> list = dao.getListOfAllAirports();
        List<Flight> flights = dao.getAllFlights();
        int count = 0;
        for(Airport air : list){
            if(!air.getAirportName().equals(airportName))continue;
            for(Flight flight : flights){
                if(flight.getFlightDate().equals(date) && (flight.getFromCity().equals(airportName) || flight.getToCity().equals(airportName))){
                    count+=dao.getNumOfPassengersAlreadyInTheFlight(flight.getFlightId());
                }
            }

        }
        return count;
    }
}
