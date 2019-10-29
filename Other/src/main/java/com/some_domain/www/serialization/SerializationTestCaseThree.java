package com.some_domain.www.serialization;

import java.io.*;

/**
 * @author : waniasra
 * @date : 10/29/2019 10:52 PM
 * This class demonstrates serialization when parent class implements Serializable interface but we don't want sub-class to be serialized
 */

/**
 * If the superclass is serializable but we don’t want the subclass to be serialized :
 * There is no direct way to prevent subclass from serialization in java.
 * One possible way by which a programmer can achieve this is by implementing the writeObject() and readObject()
 * methods in the subclass and needs to throw NotSerializableException from these methods.
 * These methods are executed during serialization and de-serialization respectively.
 * By overriding these methods, we are just implementing our own custom serialization.
 */

//Reference : https://www.geeksforgeeks.org/object-serialization-inheritance-java/
public class SerializationTestCaseThree {

    public static void main(String[] args) throws Exception {

        System.out.println("We are preventing the sub-class to be serialized.");
        ChildClazz child = new ChildClazz(1, 10);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("Other\\src\\main\\java\\com\\some_domain\\www\\serialization\\serialization-test.ser")));
        outputStream.writeObject(child);
        outputStream.close();
        System.out.println("Object has been serialized successfully.");

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("Other\\src\\main\\java\\com\\some_domain\\www\\serialization\\serialization-test.ser")));
        ChildClazz deSerializedObject = (ChildClazz) inputStream.readObject();
        System.out.println("De-serialized object is as follows.");
        System.out.println("Child Id : " + deSerializedObject.getChildId() + " , Parent Id : " + deSerializedObject.getParentId());
        inputStream.close();
    }
}

class ParentClazz implements Serializable {

    private int parentId;

    public ParentClazz() {
    }

    public ParentClazz(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}

class ChildClazz extends ParentClazz {

    private int childId;

    public ChildClazz(int parentId, int childId) {
        super(parentId);
        this.childId = childId;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    // By implementing writeObject method, we can prevent subclass from serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException();
    }

    // By implementing readObject method, we can prevent subclass from de-serialization
    private void readObject(ObjectInputStream in) throws IOException {
        throw new NotSerializableException();
    }
}
