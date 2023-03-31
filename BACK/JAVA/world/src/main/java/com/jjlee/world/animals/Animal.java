package com.jjlee.world.animals;

public class Animal {
    public final String name;

    protected Animal(String name) {
        super();
        this.name = name;
    }

    public void move() {
        System.out.println(this.name + "이/가 움직였다.");
    }
}
