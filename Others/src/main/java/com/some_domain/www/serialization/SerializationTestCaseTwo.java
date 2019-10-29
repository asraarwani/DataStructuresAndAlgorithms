package com.some_domain.www.serialization;

import java.io.*;

/**
 * @author : waniasra
 * @date : 10/29/2019 10:37 PM
 * This class demonstrates serialization when only sub-class implements Serializable interface
 */

/**
 * If a superclass is not serializable then subclass can still be serialized :
 * Even though superclass doesn’t implements Serializable interface,
 * we can serialize subclass object if subclass itself implements Serializable interface. So we can say that to serialize subclass object,
 * superclass need not to be serializable. But what happens with the instances of superclass during serialization in this case.
 * The following procedure explain this.
 * What happens when a class is serializable but its superclass is not ?
 * <p>
 * Serialization: At the time of serialization, if any instance variable is inheriting from non-serializable superclass,
 * then JVM ignores original value of that instance variable and save default value to the file.
 * <p>
 * De- Serialization: At the time of de-serialization, if any non-serializable superclass is present,
 * then JVM will execute instance control flow in the superclass. To execute instance control flow in a class,
 * JVM will always invoke default(no-arg) constructor of that class.
 * So every non-serializable superclass must necessarily contain default constructor, otherwise we will get runtime-exception.
 */
public class SerializationTestCaseTwo {

    public static void main(String[] args) throws Exception {

        ChildClass child = new ChildClass(1, 10);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("Other\\src\\main\\java\\com\\some_domain\\www\\serialization\\serialization-test.ser")));
        outputStream.writeObject(child);
        outputStream.close();
        System.out.println("Object has been serialized successfully.");

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("Other\\src\\main\\java\\com\\some_domain\\www\\serialization\\serialization-test.ser")));
        ChildClass deSerializedObject = (ChildClass) inputStream.readObject();
        System.out.println("De-serialized object is as follows and as you can see parent class field has a default value");
        System.out.println("Child Id : " + deSerializedObject.getChildId() + " , Parent Id : " + deSerializedObject.getParentId());
        inputStream.close();
    }
}

class ParentClass {

    private int parentId;

    public ParentClass() {
    }

    public ParentClass(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}

class ChildClass extends ParentClass implements Serializable {

    private int childId;

    public ChildClass(int parentId, int childId) {
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
