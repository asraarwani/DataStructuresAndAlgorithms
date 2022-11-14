package com.some_domain.www.interviewquestions;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : asraar
 * @date : 14-11-2022 02:41 pm
 */
public class ReverseVowelsOfString {

    public static void main(String[] args) {
        String string = "hello";
        ReverseVowelsOfString instance = new ReverseVowelsOfString();
        String stringWithVowelsReversed = instance.reverseVowelsOfString(string);
        System.out.println(string + " with vowels reversed is : " + stringWithVowelsReversed);
    }

    private String reverseVowelsOfString(String string) {
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
        char[] characters = string.toCharArray();
        int i = 0;
        int j = string.length() - 1;
        while (i < j) {
            while (i < j && !set.contains(characters[i])) {
                i++;
            }

            while (i < j && !set.contains(characters[j])) {
                j--;
            }

            char temporaryCharacter = characters[i];
            characters[i] = characters[j];
            characters[j] = temporaryCharacter;
            i++;
            j--;
        }
        return new String(characters);
    }
}
