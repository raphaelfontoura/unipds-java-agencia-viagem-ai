package dev.ia;

import java.util.List;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BookingTools {

    @Inject
    BookingService bookingService;

    @Tool("Obtém os detalhes de uma reserva com base em seu id (bookingId). " +
            "O ID da reserva deve ser fornecido como um número inteiro. " +
            "Retorna os detalhes da reserva, incluindo o nome do cliente, destino, datas e status.")
    public String getBookingDetails(Long bookingId) {
        return bookingService.getBookingDetails(bookingId)
                .map(Booking::toString)
                .orElse("Reserva com ID " + bookingId + " não encontrada.");
    }

    @Tool("Cancela uma reserva com base em seu id (bookingId). O usuário deve estar autenticado." +
            "O ID da reserva deve ser fornecido como um número inteiro. " +
            "Retorna os detalhes da reserva cancelada ou uma mensagem de erro se a reserva não puder ser cancelada.")
    public String cancelBooking(Long bookingId) {
        return bookingService.cancelBooking(bookingId)
                .map(Booking::toString)
                .orElse("Não foi possível cancelar a reserva. Verifique o ID da reserva e se você é o proprietário da reserva.");
    }

    @Tool("Lista os pacotes de viagem disponíveis com base na categoria fornecida (ADVENTURE ou TREASURES). " +
            "A categoria deve ser fornecida como uma string. Retorna uma lista de destinos disponíveis para a categoria especificada.")
    public String listPackagesByCategory(Category category) {
        List<Booking> packages = bookingService.findPackagesByCategory(category);
        if (packages.isEmpty()) {
            return "Nenhum pacote encontrado para a categoria: " + category;
        }
        return "Pacotes encontrados para a categoria " + category + ":\n" +
                packages.stream()
                        .map(Booking::destination)
                        .toList()
                        .toString();
    }

}
