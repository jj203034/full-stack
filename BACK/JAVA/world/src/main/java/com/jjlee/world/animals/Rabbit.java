package com.jjlee.world.animals;

public class Rabbit extends Animal {
    public Rabbit() {
        super("토끼");
    }

    @Override
    public void makeSomeNoise() {
        System.out.println("토끼가 울었다.");
    }
}
