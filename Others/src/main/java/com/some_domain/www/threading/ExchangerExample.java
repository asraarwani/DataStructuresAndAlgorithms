package com.some_domain.www.threading;

import java.util.concurrent.Exchanger;


/**
 * @author : asraar
 * @date : 10-10-2022 02:15 pm
 * <p>
 * The java.util.concurrent.Exchanger class represents a kind of rendezvous
 * point where two threads can exchange objects.
 */
public class ExchangerExample {

    public static void main(String[] args) {

        Exchanger<Student> exchanger = new Exchanger<>();

        Thread firstThread = new Thread(new ExchangerTask(new Student(1002, "Asraar", "Cananda"), exchanger), "FirstThread");
        Thread secondThread = new Thread(new ExchangerTask(new Student(1003, "James", "Germany"), exchanger), "SecondThread");

        firstThread.start();
        secondThread.start();

    }
}

class Student {

    private int id;
    private String name;
    private String address;

    public Student(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", address=" + address + "]";
    }
}

class ExchangerTask implements Runnable {

    private Student student;
    private Exchanger<Student> exchanger;

    public ExchangerTask(Student student, Exchanger<Student> exchanger) {
        this.student = student;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " had " + student.toString());
            Thread.sleep(2000);
            // Threads exchanging the contents here
            student = exchanger.exchange(student);
            System.out.println(Thread.currentThread().getName() + " now has " + student.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

