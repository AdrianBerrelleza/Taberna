package taberna.subscribers;

import taberna.eventbus.EventBus;
import taberna.events.*;

public class PanelLED {
    public PanelLED(EventBus bus) {
        bus.suscribir(PedidoRealizadoEvent.class, e ->
                System.out.println("Nuevo pedido [" + e.pedidoId() + "] mesa: " + e.mesaId() + "\u001B[0m"));

        bus.suscribir(BebidaServidaEvent.class, e ->
                System.out.println("Bebida lista para mesa " + e.mesaId() + ": " + e.bebida() + "\u001B[0m"));

        bus.suscribir(ComidaPreparadaEvent.class, e ->
                System.out.println("Comida caliente para pedido " + e.pedidoId() + ": " + e.plato() + "\u001B[0m"));

        bus.suscribir(BandaTocandoEvent.class, e ->
                System.out.println("EN VIVO: " + e.nombreBanda() + " tocando '" + e.nombreCancion() + "'\u001B[0m"));
    }
}