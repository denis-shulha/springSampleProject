package tst.springSample.logic;

import tst.springSample.annotations.InjectRandomInt;

import javax.annotation.PostConstruct;

public abstract class Speaker {

    @InjectRandomInt(min = 1, max = 100)
    private int rand;


    @PostConstruct
    public void init() {
        System.out.println("init of Speaker");
    }

    public void speak() {
        getSpeach().content();
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    protected abstract Speach getSpeach();
}
