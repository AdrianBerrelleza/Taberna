package taberna;

import taberna.eventbus.EventBus;
import taberna.events.BandaTocandoEvent;
import taberna.publishers.Banda;
import taberna.publishers.SistemaDePedidos;
import taberna.subscribers.Barra;
import taberna.subscribers.Cocina;
import taberna.subscribers.PanelLED;
import taberna.subscribers.SistemaDeSonido;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EventBus bus = new EventBus();

        // 1. Inicializar Suscriptores Fijos
        Barra barra = new Barra(bus);
        Cocina cocina = new Cocina(bus);
        new PanelLED(bus);
        new SistemaDeSonido(bus);

        // 2. DEMOSTRACIÓN DEL PRINCIPIO OPEN/CLOSED
        // Añadimos el Sistema de Humo sobre la marcha, sin modificar las clases EventBus ni Banda
        bus.suscribir(BandaTocandoEvent.class, evento -> {
            if ("Through the Fire and Flames".equals(evento.nombreCancion())) {
                System.out.println("Activando Sistema de maquinas de humo");
            }
        });

        // 3. Inicializar Publicadores
        SistemaDePedidos camarero = new SistemaDePedidos(bus);
        Banda bandaLocal = new Banda(bus, "Los Dragones Ebrios");

        // 4. Simulación del flujo
        System.out.println("--- ABRE LA TABERNA ---");

        // Pedido 1
        camarero.tomarPedido("Mesa 1", List.of("Cerveza Enana", "Estofado de Jabalí"));

        // La banda empieza a tocar normal
        Thread.sleep(500);
        bandaLocal.tocarCancion("Balada del Rey Caído", 180);

        // Pedido 2
        Thread.sleep(1000);
        camarero.tomarPedido("Mesa 4", List.of("Doble de Ron", "Pollo Asado", "Hidromiel"));

        // La banda toca la canción épica (Dispara el evento exclusivo del Sistema de Humo)
        Thread.sleep(2000);
        bandaLocal.tocarCancion("Through the Fire and Flames", 400);

        // Esperar a que terminen los hilos de preparación
        Thread.sleep(4000);
        System.out.println("--- CIERRA LA TABERNA ---");

        barra.apagar();
        cocina.apagar();
    }
}