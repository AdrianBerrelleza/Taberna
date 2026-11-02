package taberna.subscribers;

import taberna.eventbus.EventBus;
import taberna.events.BebidaServidaEvent;
import taberna.events.PedidoRealizadoEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Barra {
    private final EventBus bus;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public Barra(EventBus bus) {
        this.bus = bus;
        bus.suscribir(PedidoRealizadoEvent.class, this::prepararBebidas);
    }

    private void prepararBebidas(PedidoRealizadoEvent evento) {
        for (String item : evento.items()) {
            if (item.toLowerCase().contains("cerveza") || item.toLowerCase().contains("ron") || item.toLowerCase().contains("hidromiel")) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(1500); // Simula el tiempo de servir
                        bus.publicar(new BebidaServidaEvent(evento.mesaId(), item));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
    }

    public void apagar() { executor.shutdownNow(); }
}