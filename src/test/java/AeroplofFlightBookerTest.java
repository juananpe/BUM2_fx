import eus.ehu.business_logic.AeroplofFlightBooker;
import eus.ehu.domain.ConcreteFlight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AeroplofFlightBookerTest {
/*
    private AeroplofFlightBooker flightBooker;

    @BeforeEach
    void setUp() {
        flightBooker = new AeroplofFlightBooker();
        // Initialize with some predefined flights if necessary
    }

    private Date getTestDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2024, Calendar.JULY, 7);
        return calendar.getTime();
    }

    @Test
    void testNoAvailableFlights() {
        Date testDate = getTestDate();
        String departure = "Donostia";
        String arrival = "Lesaka";
        String fare = "Economy";
        int seats = 1;

        List<ConcreteFlight> flights = flightBooker.getMatchingConFlights(departure, arrival, testDate, fare, seats);
        assertTrue(flights.isEmpty(), "No flights should be available for the given parameters.");
    }

    @Test
    void testAvailableBusinessClassFlights() {
        Date testDate = getTestDate();
        String departure = "Donostia";
        String arrival = "Lesaka";
        String fare = "Business";
        int seats = 3;

        List<ConcreteFlight> flights = flightBooker.getMatchingConFlights(departure, arrival, testDate, fare, seats);
        assertTrue(flights.size() == 1, "One flight should be available for the given parameters.");
    }

    @Test
    void testAvailableFirstClassFlights() {
        Date testDate = getTestDate();
        String departure = "Donostia";
        String arrival = "Lesaka";
        String fare = "First";
        int seats = 3;

        List<ConcreteFlight> flights = flightBooker.getMatchingConFlights(departure, arrival, testDate, fare, seats);
        assertTrue(flights.size() == 1, "One flight should be available for the given parameters.");
    }
    @Test
    void testBookSeatSuccessfully() throws Exception {
        // Assuming a flight exists with ID "PLOF324", and it has at least 3 Economy seats available
        ConcreteFlight flight = flightBooker.getMatchingConFlights("Donostia", "Iruña", new SimpleDateFormat("d-M-yyyy").parse("7-6-2024"), "Economy", 1).get(0);
        int remainingSeats = flightBooker.bookSeat(flight, "Economy", 2);

        assertTrue(remainingSeats == 1, "Booking failed when it should have succeeded.");
    }

    @Test
    void testBookSeatWithInsufficientAvailability() throws Exception {
        // Assuming a flight exists but with insufficient seats
        ConcreteFlight flight = flightBooker.getMatchingConFlights("Donostia", "Iruña", new SimpleDateFormat("d-M-yyyy").parse("7-6-2024"), "Economy", 1).get(0);
        int remainingSeats = flightBooker.bookSeat(flight, "Economy", 100); // Trying to book 100 seats

        assertEquals(-1, remainingSeats, "Expected booking to fail due to insufficient seats, but it succeeded.");
    }
 */
}
