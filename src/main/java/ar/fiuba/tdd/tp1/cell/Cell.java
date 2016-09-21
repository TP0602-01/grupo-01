package ar.fiuba.tdd.tp1.cell;

import ar.fiuba.tdd.tp1.utilities.Observable;
import ar.fiuba.tdd.tp1.utilities.Observer;

import java.util.Collection;

/*  */
public abstract class Cell implements Observable {

    Collection<Observer> observers;

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void updateObservers() {
        for (Observer observer: this.observers){
            observer.update();
        }
    }


}
