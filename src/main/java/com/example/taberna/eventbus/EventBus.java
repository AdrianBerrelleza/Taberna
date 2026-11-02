package com.example.taberna.eventbus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    // Manejo eficiente y thread-safe de suscriptores
    private final Map<Class<?>, List<EventListener<?>>> listeners = new ConcurrentHashMap<>();

    public <T> void suscribir(Class<T> eventType, EventListener<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(listener);
    }

    public <T> void desuscribir(Class<T> eventType, EventListener<T> listener) {
        List<EventListener<?>> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    @SuppressWarnings("unchecked")
    public void publicar(Object event) {
        Class<?> eventType = event.getClass();
        List<EventListener<?>> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            for (EventListener<?> listener : eventListeners) {
                // El cast es seguro gracias al tipado de 'suscribir'
                ((EventListener<Object>) listener).onEvent(event);
            }
        }
    }
}
