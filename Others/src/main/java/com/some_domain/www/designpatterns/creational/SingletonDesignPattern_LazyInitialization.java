package com.some_domain.www.designpatterns.creational;

public class SingletonDesignPattern_LazyInitialization {

    public static void main(String[] args) {

        SingletonClass singletonClass = SingletonClass.getInstance();
        System.out.println(singletonClass.hashCode());

        singletonClass = SingletonClass.getInstanceAlternate();
        System.out.println(singletonClass.hashCode());
    }
}

class SingletonClass {

    private static SingletonClass instance = null;

    private SingletonClass() {

    }

    public static synchronized SingletonClass getInstance() {
        if (instance == null) {
            instance = new SingletonClass();
        }
        return instance;
    }

    public static SingletonClass getInstanceAlternate() {
        if (instance == null) {
            synchronized (SingletonClass.class) {
                if (instance == null) {
                    instance = new SingletonClass();
                }
            }
        }
        return instance;
    }
}
