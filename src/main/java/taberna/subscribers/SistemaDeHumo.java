package taberna.subscribers;

import taberna.eventbus.EventBus;
import taberna.events.BandaTocandoEvent;

public class SistemaDeHumo {
    public SistemaDeHumo(EventBus bus) {
        bus.suscribir(BandaTocandoEvent.class, evento -> {
            System.out.println("[Efectos] Lanzando humo en el escenario para: " + evento.nombreBanda());
        });
    }
}