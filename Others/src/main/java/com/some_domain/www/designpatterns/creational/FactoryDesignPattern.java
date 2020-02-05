package com.some_domain.www.designpatterns.creational;

public class FactoryDesignPattern {

    public static void main(String[] args) {

        FactoryDesignPattern instance = new FactoryDesignPattern();

        Shape shape = instance.shapeFactory(3);
        System.out.println(shape.getShapeName());

        shape = instance.shapeFactory(4);
        System.out.println(shape.getShapeName());
    }

    public Shape shapeFactory(int numberOfSides) {
        if (numberOfSides == 3) {
            return new Triangle();
        }
        if (numberOfSides == 4) {
            return new Square();
        }
        return null;
    }
}


interface Shape {
    public String getShapeName();
}


class Triangle implements Shape {

    @Override
    public String getShapeName() {
        return "Triangle";
    }
}

class Square implements Shape {

    @Override
    public String getShapeName() {
        return "Square";
    }
}