package ar.fiuba.tdd.tp1.utilities;

/**
 * Created by juanma on 21/09/16.
 */
public interface Observable {
    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    void updateObservers();
}
