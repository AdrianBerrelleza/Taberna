package taberna.publishers;

import taberna.eventbus.EventBus;
import taberna.events.PedidoRealizadoEvent;
import java.util.List;
import java.util.UUID;

public class SistemaDePedidos {
    private final EventBus bus;

    public SistemaDePedidos(EventBus bus) {
        this.bus = bus;
    }

    public void tomarPedido(String mesaId, List<String> items) {
        String pedidoId = UUID.randomUUID().toString().substring(0, 8);
        bus.publicar(new PedidoRealizadoEvent(mesaId, pedidoId, items));
    }
}