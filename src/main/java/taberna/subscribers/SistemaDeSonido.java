package taberna.subscribers;

import taberna.eventbus.EventBus;
import taberna.events.BandaTocandoEvent;

public class SistemaDeSonido {
    public SistemaDeSonido(EventBus bus) {
        bus.suscribir(BandaTocandoEvent.class, evento -> {
            System.out.println("[Sonido] Ajustando ecualizador para la banda: " + evento.nombreBanda());
        });
    }
}