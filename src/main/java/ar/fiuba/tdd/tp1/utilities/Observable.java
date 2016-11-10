package ar.fiuba.tdd.tp1.utilities;

import java.util.ArrayList;
import java.util.Collection;

/*
 * Observable represent an Observable object from Observer pattern.
 * Have a Collection of OBservers and when is updated, update all
 * observers in the collection
 *
 */
public abstract class Observable {

    private Collection<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void updateObservers() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }

}
