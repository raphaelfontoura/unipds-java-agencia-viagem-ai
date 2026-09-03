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
        bookings.put(
                67898L,
                new Booking(
                        67898L,
                        "John Doe",
                        "Amazon",
                        LocalDate.of(2024, 6, 1),
                        LocalDate.of(2024, 6, 8),
                        BookingStatus.CONFIRMED));

        bookings.put(
                98765L,
                new Booking(
                        98765L,
                        "Jane Smith",
                        "Salvador",
                        LocalDate.of(2024, 7, 15),
                        LocalDate.of(2024, 7, 18),
                        BookingStatus.PENDING));

        bookings.put(
                54321L,
                new Booking(
                        54321L,
                        "Alice Johnson",
                        "Egypt",
                        LocalDate.of(2024, 8, 5),
                        LocalDate.of(2024, 8, 12),
                        BookingStatus.CANCELLED));

        bookings.put(
                11223L,
                new Booking(
                        11223L,
                        "Bob Wilson",
                        "Japan",
                        LocalDate.of(2024, 9, 10),
                        LocalDate.of(2024, 9, 18),
                        BookingStatus.CONFIRMED));
    }

    public Optional<Booking> getBookingDetails(Long bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public Optional<Booking> cancelBooking(Long bookingId, String lastName) {
        if (bookings.containsKey(bookingId)) {
            Booking booking = bookings.get(bookingId);
            if (booking.customerName().endsWith(lastName)) {
                Booking cancelledBooking = new Booking(
                        booking.id(),
                        booking.customerName(),
                        booking.destination(),
                        booking.startDate(),
                        booking.endDate(),
                        BookingStatus.CANCELLED);
                bookings.put(bookingId, cancelledBooking);
                return Optional.of(cancelledBooking);
            }
        }
        return Optional.empty();
    }

}
