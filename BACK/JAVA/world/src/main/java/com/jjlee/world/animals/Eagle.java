package com.jjlee.world.animals;

public class Eagle extends Animal implements Flyable {
    public Eagle() {
        super("독수리");
    }

    @Override
    public void walk() {
        System.out.println("독수리가 뒤뚱뒤뚱 걸었다.");
    }

    public void walk(String target) {
        System.out.println("독수리가 "+ target + "뒤뚱뒤뚱 걸었다.");
    }

    @Override
    public void makeSomeNoise() {
        System.out.println("독수리가 울었다.");
    }

    @Override
    public void fly() {
        System.out.println("독수리가 날았다.");
    }
}
