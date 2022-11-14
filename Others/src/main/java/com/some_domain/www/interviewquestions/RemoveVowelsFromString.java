package com.some_domain.www.interviewquestions;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : asraar
 * @date : 14-11-2022 03:13 pm
 */
public class RemoveVowelsFromString {

    public static void main(String[] args) {
        String string = "asraar";
        RemoveVowelsFromString instance = new RemoveVowelsFromString();
        String stringWithVowelsRemoved = instance.removeVowelsFromString(string);
        System.out.println("After removing vowels from string " + string + " is now : " + stringWithVowelsRemoved);
    }

    private String removeVowelsFromString(String string) {
        Set<Character> set = new HashSet<>();
        set.add('A');
        set.add('a');
        set.add('E');
        set.add('e');
        set.add('I');
        set.add('i');
        set.add('O');
        set.add('o');
        set.add('U');
        set.add('u');
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (!set.contains(string.charAt(i))) {
                stringBuilder.append(string.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}
