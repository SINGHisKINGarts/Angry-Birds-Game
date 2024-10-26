package com.hitesh.angrybird;
import com.badlogic.gdx.graphics.Texture;

public abstract class Bird {
    private double speed;
    private double impactPower;
    private double weight;
    private int maxHits;
    private Texture texture;
    private String ability;

    // Constructor
    public Bird(double speed, double impactPower, double weight, int maxHits, Texture texture, String ability) {
        this.speed = speed;
        this.impactPower = impactPower;
        this.weight = weight;
        this.maxHits = maxHits;
        this.texture = texture;
        this.ability = ability;
    }
}