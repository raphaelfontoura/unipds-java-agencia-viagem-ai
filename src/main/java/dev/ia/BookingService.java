package dev.ia;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookingService {

    private final Map<Long, Booking> bookings = new HashMap<>();

    public BookingService() {
        bookings.put(1L, new Booking(1L, "John Doe", "Paris", LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 10), BookingStatus.CONFIRMED));
        bookings.put(2L, new Booking(2L, "Jane Smith", "New York", LocalDate.of(2024, 7, 15), LocalDate.of(2024, 7, 20), BookingStatus.PENDING));
        bookings.put(3L, new Booking(3L, "Alice Johnson", "Tokyo", LocalDate.of(2024, 8, 5), LocalDate.of(2024, 8, 15), BookingStatus.CANCELLED));
    }

    public Optional<Booking> getBookingDetails(Long bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public Optional<Booking> cancelBooking(Long bookingId, String customerLastName) {
        if (bookings.containsKey(bookingId)) {
            Booking booking = bookings.get(bookingId);
            if (booking.customerName().endsWith(customerLastName)) {
                Booking cancelledBooking = new Booking(
                    booking.id(),
                    booking.customerName(),
                    booking.destination(),
                    booking.startDate(),
                    booking.endDate(),
                    BookingStatus.CANCELLED
                );
                bookings.put(bookingId, cancelledBooking);
                return Optional.of(cancelledBooking);
            }
        }
        return Optional.empty();
    }

}
