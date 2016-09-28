package ar.fiuba.tdd.tp1.utilities;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable {

    private Collection<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void updateObservers() {
        for (Observer observer: this.observers) {
            observer.update();
        }
    }

}
