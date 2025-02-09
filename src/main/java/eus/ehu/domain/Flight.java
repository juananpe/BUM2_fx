package eus.ehu.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Flight
 *
 * An object of this class represents an actual air link between two cities
 * The objects of the complementary class "Concrete Flight" represent actual
 * flights scheduled for this air link
 */
public class Flight {

	private String flightCode;
	private String departureCity;
	private String arrivalCity;
	private List<ScheduledFlight> scheduledFlights;

	public Flight(String flightCode, String departureCity, String arrivalCity) {
		super();
		this.flightCode = flightCode;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.scheduledFlights = new ArrayList<ScheduledFlight>();
	}


	public String getFlightCode() {
		return flightCode;
	}


	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}


	public String getDepartureCity() {
		return departureCity;
	}


	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}


	public String getArrivalCity() {
		return arrivalCity;
	}


	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}


	public void addScheduledFlight(ScheduledFlight conFl) {
		scheduledFlights.add(conFl);
	}


	public ArrayList<ScheduledFlight> getScheduledFlights() {
		ArrayList<ScheduledFlight> l = new ArrayList<ScheduledFlight>();
		l.addAll(scheduledFlights);
		return l;
	}


	public ArrayList<ScheduledFlight> getScheduledFlights(Date date) {
		ArrayList<ScheduledFlight> lInDate = new ArrayList<ScheduledFlight>();
		// Convert the input date to LocalDate
		LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		for (ScheduledFlight cfl : scheduledFlights) {
			// Convert each ConcreteFlight's date to LocalDate
			LocalDate flightDate = cfl.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			// Compare only the date parts
			if (inputDate.equals(flightDate))
				lInDate.add(cfl);
		}

		return lInDate;
	}


	public void setConcreteFlights(List<ScheduledFlight> scheduledFlights) {
		this.scheduledFlights = scheduledFlights;
	}


	public String toString() {
		return flightCode + ": " + departureCity + " -> " + arrivalCity;
	}

}
