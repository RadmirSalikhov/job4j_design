package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Animal {
    @Override
    public String toString() {
        return "Animal";
    }
}

class Predator extends Animal {
    @Override
    public String toString() {
        return "Predator";
    }
}

class Tiger extends Predator {
    @Override
    public String toString() {
        return "Tiger";
    }
}

public class Generics {
    public static void main(String[] args) {
        Generics generics = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        generics.printObject(first);
        generics.printObject(second);
        generics.printObject(third);
        System.out.println();

        /*
        generics.printBoundedWildCard(first);
         */
        generics.printBoundedWildCard(second);
        generics.printBoundedWildCard(third);
        System.out.println();

        generics.printLowerBoundedWildCard(first);
        generics.printLowerBoundedWildCard(second);
        /*
        generics.printLowerBoundedWildCard(third);
         */

    }

    public void printObject(List<?> list) {
        /*
        for (Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
        */
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        /*
        for (Iterator<Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();

         */
        for (Predator next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        /*
        for (Iterator<Predator> iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
         */
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }
}