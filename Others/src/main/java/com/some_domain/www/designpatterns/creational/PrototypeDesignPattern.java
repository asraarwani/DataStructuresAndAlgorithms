package com.some_domain.www.designpatterns.creational;

import java.util.LinkedHashMap;
import java.util.Map;

public class PrototypeDesignPattern {

    public static void main(String[] args) {

        Color color = ColorStore.getColor("Blue");
        color.addColor();

        color = ColorStore.getColor("Black");
        color.addColor();
    }
}

class ColorStore {

    private static Map<String, Color> colorStore = new LinkedHashMap<>();

    static {
        colorStore.put("Blue", new BlueColor());
        colorStore.put("Black", new BlackColor());
    }

    public static Color getColor(String colorName) {
        return (Color) colorStore.get(colorName).clone();
    }
}

class BlackColor extends Color {

    public BlackColor() {
        this.color = "Black";
    }

    @Override
    void addColor() {
        System.out.println("Black color added");
    }
}

class BlueColor extends Color {

    public BlueColor() {
        this.color = "Blue";
    }

    @Override
    void addColor() {
        System.out.println("Blue color added");
    }
}

abstract class Color implements Cloneable {

    protected String color;

    abstract void addColor();

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}