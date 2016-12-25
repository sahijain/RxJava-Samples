package com.example.rxjavademo.rx_bus;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by sahil on 18/12/16.
 */

public class RxBus {

    private static RxBus sRxBus;
    private static Object sObjectLock = new Object();
    private PublishSubject<Object> rxBus = PublishSubject.create();

    public static RxBus getInstance() {
        if(sRxBus == null)
            synchronized (sObjectLock) {
                if (sRxBus == null) {
                    sRxBus = new RxBus();
                }
            }

        return sRxBus;
    }

    public void send(Object object){
        if(hasObservers())
            rxBus.onNext(object);
    }

    public boolean hasObservers(){
        return rxBus.hasObservers();
    }

    public Observable<Object> asObserveable(){
        return rxBus.asObservable();
    }
}
