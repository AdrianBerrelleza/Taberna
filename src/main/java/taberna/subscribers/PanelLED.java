package taberna.subscribers;

import taberna.eventbus.EventBus;
import taberna.events.*;

public class PanelLED {
    public PanelLED(EventBus bus) {
        bus.suscribir(PedidoRealizadoEvent.class, e ->
                System.out.println("\u001B[Nuevo pedido [" + e.pedidoId() + "] mesa: " + e.mesaId() + "\u001B[0m"));

        bus.suscribir(BebidaServidaEvent.class, e ->
                System.out.println("\u001B[Bebida lista para mesa " + e.mesaId() + ": " + e.bebida() + "\u001B[0m"));

        bus.suscribir(ComidaPreparadaEvent.class, e ->
                System.out.println("\u001B[Comida caliente para pedido " + e.pedidoId() + ": " + e.plato() + "\u001B[0m"));

        bus.suscribir(BandaTocandoEvent.class, e ->
                System.out.println("\u001B[EN VIVO: " + e.nombreBanda() + " tocando '" + e.nombreCancion() + "'\u001B[0m"));
    }
}