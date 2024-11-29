package com.hitesh.angrybird;

import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsObject {
        Body body;
        String type;
        float maxHealth;
        float currentHealth;
        int hitPoints; // Number of hits to destroy

        PhysicsObject(Body body, String type) {
            this.body = body;
            this.type = type;

            // Define health and hit points based on material/type
            switch (type) {
                // MATERIALS
                case "WOOD":
                    this.maxHealth = 100;
                    this.currentHealth = 100;
                    this.hitPoints = 1;
                    break;
                case "GLASS":
                    this.maxHealth = 50;
                    this.currentHealth = 50;
                    this.hitPoints = 1;
                    break;
                case "STEEL":
                    this.maxHealth = 200;
                    this.currentHealth = 200;
                    this.hitPoints = 2;
                    break;

                // PIGS
                case "SMALL_PIG":
                    this.maxHealth = 50;
                    this.currentHealth = 50;
                    this.hitPoints = 1;
                    break;
                case "MEDIUM_PIG":
                    this.maxHealth = 100;
                    this.currentHealth = 100;
                    this.hitPoints = 2;
                    break;
                case "LARGE_PIG":
                    this.maxHealth = 150;
                    this.currentHealth = 150;
                    this.hitPoints = 3;
                    break;

                default:
                    this.maxHealth = 100;
                    this.currentHealth = 100;
                    this.hitPoints = 1;
            }
        }

        public boolean takeDamage(float damage) {
            currentHealth -= damage;
            return currentHealth <= 0;
        }

        public boolean shouldDestroy() {
            return currentHealth <= 0;
        }
    }

