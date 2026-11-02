package taberna.eventbus;

@FunctionalInterface
public interface EventListener<T> {
    void onEvent(T event);
}