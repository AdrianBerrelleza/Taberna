package taberna;

import taberna.eventbus.EventBus;
import taberna.publishers.*;
import taberna.subscribers.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EventBus bus = new EventBus();

        // Suscriptores
        Barra barra = new Barra(bus);
        Cocina cocina = new Cocina(bus);
        new PanelLED(bus);
        new SistemaDeSonido(bus);
        new SistemaDeHumo(bus);

        // Publicadores
        SistemaDePedidos pedidos = new SistemaDePedidos(bus);
        Banda banda = new Banda(bus, "Los Bardos del Metal");

        // Simulación
        pedidos.tomarPedido("Mesa 4", List.of("Cerveza", "Estofado de Jabalí", "Hidromiel"));
        banda.tocarCancion("Sinfonía del Dragón", 240);

        // Tiempo para permitir la ejecución de los hilos asíncronos de Barra y Cocina
        Thread.sleep(4000);

        barra.apagar();
        cocina.apagar();
    }
}