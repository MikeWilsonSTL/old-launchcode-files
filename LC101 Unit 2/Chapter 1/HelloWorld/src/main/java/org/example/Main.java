package org.example;
import org.example.Pet;

//Assume that we add two methods to a Pet class—public String makeNoise() and public static void increaseAge(). Which of the following statements is true?

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Pet test = new Pet();

        test.makeNoise();
        Pet.increaseAge();
    }
}