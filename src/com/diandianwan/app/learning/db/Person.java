package com.diandianwan.app.learning.db;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Yang
 */
public class Person {
    private String name;
    private int balance;
    private int id;

    public Person(int anInt, String name, int balance) {
        this.id = anInt;
        this.name =null;
        this.balance = balance;

    }

    public String getName() {
        return name;
    }

    public int  getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }
}
