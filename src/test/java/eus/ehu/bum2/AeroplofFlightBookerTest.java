package eus.ehu.bum2;

import eus.ehu.business_logic.AeroplofFlightBooker;
import eus.ehu.domain.Flight;
import eus.ehu.domain.ScheduledFlight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AeroplofFlightBookerTest {

    private AeroplofFlightBooker flightBooker;

    @BeforeEach
    void setUp() {
        flightBooker = new AeroplofFlightBooker();
        // Initialize with some predefined flights if necessary
    }

    private Date getTestDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2025, Calendar.JULY, 7);
        return calendar.getTime();
    }

    @Test
    void testNoAvailableFlights() {
        Date testDate = getTestDate();
        String departure = "Donostia";
        String arrival = "Lesaka";
        String fare = "Economy";
        int seats = 1;

        List<ScheduledFlight> flights = flightBooker.getMatchingConFlights(departure, arrival, testDate, fare, seats);
        assertTrue(flights.isEmpty(), "No flights should be available for the given parameters.");
    }

    @Test
    void testAvailableBusinessClassFlights() {
        Date testDate = getTestDate();
        String departure = "Donostia";
        String arrival = "Lesaka";
        String fare = "Business";
        int seats = 3;

        List<ScheduledFlight> flights = flightBooker.getMatchingConFlights(departure, arrival, testDate, fare, seats);
        assertTrue(flights.size() == 1, "One flight should be available for the given parameters.");
    }

    @Test
    void testAvailableFirstClassFlights() {
        Date testDate = getTestDate();
        String departure = "Donostia";
        String arrival = "Lesaka";
        String fare = "First";
        int seats = 3;

        List<ScheduledFlight> flights = flightBooker.getMatchingConFlights(departure, arrival, testDate, fare, seats);
        assertTrue(flights.size() == 1, "One flight should be available for the given parameters.");
    }
    @Test
    void testBookSeatSuccessfully() throws Exception {
        // Assuming a flight exists with ID "PLOF324", and it has at least 3 Economy seats available
        ScheduledFlight flight = flightBooker.getMatchingConFlights("Donostia", "Iruña", new SimpleDateFormat("d-M-yyyy").parse("7-6-2025"), "Economy", 1).get(0);
        int remainingSeats = flightBooker.bookSeat(flight, "Economy", 2);

        assertTrue(remainingSeats == 1, "Booking failed when it should have succeeded.");
    }

    @Test
    void testBookSeatWithInsufficientAvailability() throws Exception {
        // Assuming a flight exists but with insufficient seats
        ScheduledFlight flight = flightBooker.getMatchingConFlights("Donostia", "Iruña", new SimpleDateFormat("d-M-yyyy").parse("7-6-2025"), "Economy", 1).get(0);
        int remainingSeats = flightBooker.bookSeat(flight, "Economy", 100); // Trying to book 100 seats

        assertEquals(-1, remainingSeats, "Expected booking to fail due to insufficient seats, but it succeeded.");
    }

    @Test
    void testAllocateSeatSuccessfully() {
        // Assuming a ConcreteFlight instance with available seats in Economy class
        ScheduledFlight flight = new ScheduledFlight("TestFlight", new Date(), 10, 5, 5, "12:00", new Flight("DEP-ARR", "DepartureCity", "ArrivalCity"));
        String fare = "Economy";
        int seatsToAllocate = 1;

        int remainingSeats = flight.allocateSeat(fare, seatsToAllocate);

        assertTrue(remainingSeats >= 0, "Should successfully allocate seat and return non-negative remaining seats.");
        assertEquals(4, remainingSeats, "After allocating one seat, remaining seats should be reduced by one.");
    }

    @Test
    void testAllocateSeatFailureDueToInsufficientSeats() {
        // Assuming a ConcreteFlight instance with 2 First seats left
        ScheduledFlight flight = new ScheduledFlight("TestFlight", new Date(), 2, 5, 5, "12:00", new Flight("DEP-ARR", "DepartureCity", "ArrivalCity"));
        String fare = "First";
        int seatsToAllocate = 3; // Attempting to allocate more seats than available

        int remainingSeats = flight.allocateSeat(fare, seatsToAllocate);

        assertEquals(-1, remainingSeats, "Allocating more seats than available should fail and return -1.");
    }


}
