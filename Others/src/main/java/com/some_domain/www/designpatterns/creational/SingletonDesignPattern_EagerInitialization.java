package com.some_domain.www.designpatterns.creational;

public class SingletonDesignPattern_EagerInitialization {

    public static void main(String[] args) {

        Singleton s = Singleton.getInstance();
        System.out.println(s.hashCode());

        Singleton t = Singleton.getInstance();
        System.out.println(t.hashCode());
    }
}


class Singleton {

    private Singleton() {

    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final Singleton instance = new Singleton();
    }
}


