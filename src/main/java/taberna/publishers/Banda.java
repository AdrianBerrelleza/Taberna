package taberna.publishers;

import taberna.eventbus.EventBus;
import taberna.events.BandaTocandoEvent;

public class Banda {
    private final EventBus bus;
    private final String nombre;

    public Banda(EventBus bus, String nombre) {
        this.bus = bus;
        this.nombre = nombre;
    }

    public void tocarCancion(String cancion, int duracion) {
        bus.publicar(new BandaTocandoEvent(nombre, cancion, duracion));
    }
}