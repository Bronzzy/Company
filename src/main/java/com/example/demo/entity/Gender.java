package com.example.demo.entity;

public enum Gender {
    //    MALE, FEMALE, OTHERS
    MALE(0),
    FEMALE(1);
    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
