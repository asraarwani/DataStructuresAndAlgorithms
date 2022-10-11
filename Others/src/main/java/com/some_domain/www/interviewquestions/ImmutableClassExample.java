package com.some_domain.www.interviewquestions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 11-10-2022 03:55 pm
 */
public class ImmutableClassExample {

    public static void main(String[] args) {

        Map<String, String> addressMap = new HashMap<>();
        addressMap.put("City", "Bangalore");
        addressMap.put("State", "Karnataka");
        addressMap.put("Zip", "560083");
        addressMap.put("Address", "Jayanagar Bangalore");

        ImmutableClass immutableClass = new ImmutableClass(100, "Asraar", addressMap);

        System.out.println("Id : " + immutableClass.getId());
        System.out.println("Name : " + immutableClass.getName());
        System.out.println("Address : " + immutableClass.getMap());

        //Remains unchanged due to deep copy in constructor
        System.out.println();
        addressMap.put("Street", "2nd Main Road");
        System.out.println("Address : " + immutableClass.getMap());

        //Remains unchanged due to deep copy in getter
        immutableClass.getMap().put("Street", "2nd Main Road");
        System.out.println("Address : " + immutableClass.getMap());
    }
}

final class ImmutableClass {

    private final int id;
    private final String name;
    private final Map<String, String> map;

    public ImmutableClass(int id, String name, Map<String, String> map) {
        this.id = id;
        this.name = name;
        Map<String, String> newMap = new LinkedHashMap<>();
        map.entrySet().stream().forEach(entry -> {
            newMap.put(entry.getKey(), entry.getValue());
        });
        this.map = newMap;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getMap() {
        Map<String, String> newMap = new HashMap<>();
        this.map.entrySet().stream().forEach(entry -> {
            newMap.put(entry.getKey(), entry.getValue());
        });
        return newMap;
    }
}

