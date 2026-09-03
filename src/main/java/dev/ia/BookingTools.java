package dev.ia;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BookingTools {

    @Inject
    BookingService bookingService;

    @Tool(
            name = "get_booking_details",
            value = "Obtém os detalhes de uma reserva com base em seu id (bookingId). " +
                    "O ID da reserva deve ser fornecido como um número inteiro. " +
                    "Retorna os detalhes da reserva, incluindo o nome do cliente, destino, datas e status."
    )
    public String getBookingDetails(Long bookingId) {
        return bookingService.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Reserva com ID " + bookingId + " não encontrada.");
    }

    @Tool(
            name = "cancel_booking",
            value = "Cancela uma reserva com base em seu id (bookingId) e no sobrenome do cliente (lastName). " +
                    "O ID da reserva deve ser fornecido como um número inteiro, e o sobrenome do cliente deve ser fornecido como uma string. " +
                    "Se a reserva for encontrada e o sobrenome corresponder, a reserva será cancelada e os detalhes atualizados serão retornados."
    )
    public String cancelBooking(Long bookingId, String lastName) {
        return bookingService.cancelBooking(bookingId, lastName)
                .map(Booking::toString)
                .orElse("Não foi possível cancelar a reserva. Verifique o ID da reserva e o sobrenome do cliente.");
    }

}
