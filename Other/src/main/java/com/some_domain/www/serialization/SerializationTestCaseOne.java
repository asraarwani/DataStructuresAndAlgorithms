package com.some_domain.www.serialization;

import java.io.*;

/**
 * @author : waniasra
 * @date : 10/29/2019 10:21 PM
 * This class demonstrates serialization when only parent class implements Serializable interface and child class is by default serializable
 */

/**
 * If superclass is serializable then subclass is automatically serializable : If superclass is Serializable, then by default every subclass is serializable.
 * Hence, even though subclass doesn’t implement Serializable interface( and if it’s superclass implements Serializable), then we can serialize subclass object.
 */

//Reference : https://www.geeksforgeeks.org/object-serialization-inheritance-java/
public class SerializationTestCaseOne {

    public static void main(String[] args) throws Exception {

        //If superclass is serializable then subclass is automatically serializable

        Child child = new Child(1, 10);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("Other\\src\\main\\java\\com\\some_domain\\www\\serialization\\serialization-test.ser")));
        outputStream.writeObject(child);
        outputStream.close();
        System.out.println("Object has been serialized successfully.");

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("Other\\src\\main\\java\\com\\some_domain\\www\\serialization\\serialization-test.ser")));
        Child deSerializedObject = (Child) inputStream.readObject();
        System.out.println("De-serialized object is as follows and as you can see child class field is also serialized and de-serialized");
        System.out.println("Child Id : " + deSerializedObject.getChildId() + " , Parent Id : " + deSerializedObject.getParentId());
        inputStream.close();
    }
}

class Parent implements Serializable {

    private int parentId;

    public Parent() {
    }

    public Parent(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}

class Child extends Parent {

    private int childId;

    public Child(int parentId, int childId) {
        super(parentId);
        this.childId = childId;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }
}
