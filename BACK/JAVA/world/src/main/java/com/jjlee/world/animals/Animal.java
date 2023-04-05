package com.jjlee.world.animals;

public abstract class Animal extends Object {
    public final String name;

    protected Animal(String name) {
        super();
        this.name = name;
    }

    public abstract void makeSomeNoise();
    public void walk() {
        System.out.println(this.name + "이(가) 걸었다.");
    }
}
