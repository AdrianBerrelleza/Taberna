package taberna.subscribers;

import taberna.eventbus.EventBus;
import taberna.events.ComidaPreparadaEvent;
import taberna.events.PedidoRealizadoEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cocina {
    private final EventBus bus;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public Cocina(EventBus bus) {
        this.bus = bus;
        bus.suscribir(PedidoRealizadoEvent.class, this::cocinar);
    }

    private void cocinar(PedidoRealizadoEvent evento) {
        for (String item : evento.items()) {
            if (item.toLowerCase().contains("carne") || item.toLowerCase().contains("estofado") || item.toLowerCase().contains("pollo")) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(3000); // Simula el tiempo de cocinar (más lento que la bebida)
                        bus.publicar(new ComidaPreparadaEvent(evento.pedidoId(), item));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
    }

    public void apagar() { executor.shutdownNow(); }
}