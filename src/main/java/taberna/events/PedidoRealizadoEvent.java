package taberna.events;
import java.util.List;

public record PedidoRealizadoEvent(String mesaId, String pedidoId, List<String> items) {}