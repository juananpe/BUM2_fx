package eus.ehu.business_logic;

import eus.ehu.domain.ConcreteFlight;
import eus.ehu.domain.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * AeroplofFlightBooker
 *
 * The class that implements the business logic controller for the "Select Flight"
 * use case in the project "Aeroplof". It provides domain related information to
 * the GUI's of the use cases that deal with flights
 */
public class AeroplofFlightBooker implements FlightBooker {

	List<Flight> availableFlights;

	public AeroplofFlightBooker() {

		// This section is for testing purposes and it's not truly related to
		// the construction process. A couple of flights are created and
		// included in the list 'availableFlights'. Then associated concrete
		// flights are also created and uploaded in main memory.
		// Please note that they will be accessed from the flights to whom they
		// belong (which are safe in 'availableFlights').
		//
		// These fictitious flights and concrete flights serve to test if the
		// use case is correctly implemented prior to establishing persistence.

		availableFlights = new ArrayList<Flight>();
		Flight flight1 = new Flight("EAS-PNA", "Donostia", "Iruña");
		availableFlights.add(flight1);
		Flight flight2 = new Flight("EAS-LSK", "Donostia", "Lesaka");
		availableFlights.add(flight2);


		// Get current year as a 2 digit String
		String year = new SimpleDateFormat("yy", Locale.ENGLISH).format(new Date());


		new ConcreteFlight("PLOF324", parseDateTime("7-6-" + year + " 10:00"), 1, 2, 3, "10:00", flight1);
		new ConcreteFlight("PLOF020", parseDateTime("7-6-" + year + " 11:00"), 4, 3, 20, "11:00", flight1);
		new ConcreteFlight("PLOF021", parseDateTime("7-7-" + year + " 12:00"), 0, 0, 0, "12:00", flight1);
		new ConcreteFlight("PLOF022", parseDateTime("7-7-" + year + " 13:00"), 1, 3, 2, "13:00", flight1);
		new ConcreteFlight("PLOF023", parseDateTime("7-7-" + year + " 14:00"), 0, 3, 7, "14:00", flight1);
		new ConcreteFlight("PLOF024", parseDateTime("7-7-" + year + " 15:00"), 0, 0, 1, "15:00", flight1);
		new ConcreteFlight("PLOF025", parseDateTime("7-7-" + year + " 16:00"), 2, 4, 1, "16:00", flight1);
		new ConcreteFlight("PLOF026", parseDateTime("7-7-" + year + " 17:00"), 3, 3, 0, "17:00", flight1);
		new ConcreteFlight("PLOF027", parseDateTime("7-7-" + year + " 18:00"), 3, 5, 12, "18:00", flight1);
		new ConcreteFlight("PLOF028", parseDateTime("7-7-" + year + " 19:00"), 3, 3, 0, "19:00", flight1);
		new ConcreteFlight("PLOF029", parseDateTime("7-7-" + year + " 20:00"), 2, 4, 1, "20:00", flight1);
		new ConcreteFlight("PLOF030", parseDateTime("7-7-" + year + " 21:00"), 3, 6, 10, "21:00", flight1);
		new ConcreteFlight("PLOF031", parseDateTime("7-7-" + year + " 22:00"), 0, 3, 4, "22:00", flight1);
		new ConcreteFlight("PLOF032", parseDateTime("7-7-" + year + " 23:00"), 0, 2, 11, "23:00", flight1);
		new ConcreteFlight("PLOF087", parseDateTime("6-6-" + year + " 10:00"), 13, 0, 0, "10:00", flight2);
		new ConcreteFlight("PLOF264", parseDateTime("7-6-" + year + " 11:00"), 3, 6, 10, "11:00", flight2);
		new ConcreteFlight("PLOF433", parseDateTime("7-7-" + year + " 12:00"), 3, 3, 0, "12:00", flight2);

	}

	/**
	 * Parses a date and time string in the format "yyyy-MM-dd HH:mm"
	 * (24-hour format) and returns a java.util.Date object.
	 *
	 * @param dateTimeStr The date and time string to parse.
	 * @return A java.util.Date object representing the specified date and time.
	 */
	public static Date parseDateTime(String dateTimeStr) {
		// Updated pattern for 24-hour format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yy HH:mm");
		LocalDateTime localDateTime;
		try {
			localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
		} catch (Exception e) {
			throw new IllegalArgumentException("Error parsing date time: " + dateTimeStr, e);
		}

		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}

	/**
	 * 'getConFlights' method provides a List of concrete flights matching
	 * some user's requirements
	 *
	 * @param intendedDepartureCity    introduced by user
	 * @param intendedArrivalCity      introduced by user
	 * @param intendedDate             introduced/selected by user
	 * @return                         List of concrete flights
	 */
	public List<ConcreteFlight> getMatchingConFlights(String intendedDepartureCity,
			String intendedArrivalCity, Date intendedDate) {

		List<ConcreteFlight> matchingConFlights = new ArrayList<ConcreteFlight>();

		for (Flight fli : availableFlights) {
			if (fli.getArrivalCity().equals(intendedArrivalCity) &&
					fli.getDepartureCity().equals(intendedDepartureCity))
				matchingConFlights.addAll(fli.getConcreteFlights(intendedDate));
		}

		return matchingConFlights;

	}

	/**
	 * @param conFli		The concrete flight in which a free seat is to be booked
	 * @param fare			The fare of the ticket
	 * @return				The number of remaining free seats for this fare after
	 * 						the booking, or -1 if no available seat to book
	 */
	public int bookSeat(ConcreteFlight conFli, String fare) {
		return conFli.allocateSeat(fare, 1);
	}

}
