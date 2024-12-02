package com.hitesh.angrybird;

import com.badlogic.gdx.math.Vector2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GAmeSavelev2 implements Serializable {
    public static void saveGame(Level2 level) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lvl2_game_save.ser"))) {
            out.writeObject(new SaveData(level));
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadGame(Level2 level) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("lvl2_game_save.ser"))) {
            SaveData saveData = (SaveData) in.readObject();
            saveData.restoreGameState(level);
            System.out.println("Game loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class SaveData implements Serializable {
        Vector2 birdPosition;
        Vector2 birdVelocity;
        boolean birdLaunched;
        int remainingBirds;
        int score;
        List<BlockData> blockStates;
        List<Vector2> pigPositions;

        SaveData(Level2 level) {
            this.birdPosition = new Vector2(level.birdPosition);
            this.birdVelocity = new Vector2(level.birdVelocity);
            this.birdLaunched = level.birdLaunched;
            this.remainingBirds = level.remainingBirds;
            this.score = level.score;

            this.blockStates = level.blocks.stream()
                    .map(b -> new BlockData(b.getClass().getSimpleName(), b.getX(), b.getY()))
                    .collect(Collectors.toList());

            this.pigPositions = new ArrayList<>(level.pigs);
        }

        void restoreGameState(Level2 level) {
            level.birdPosition.set(this.birdPosition);
            level.birdVelocity.set(this.birdVelocity);
            level.birdLaunched = this.birdLaunched;
            level.remainingBirds = this.remainingBirds;
            level.score = this.score;

            level.blocks.clear();
            for (BlockData blockData : this.blockStates) {
                switch (blockData.blockType) {
                    case "WoodenBlock":
                        level.blocks.add(new WoodenBlock(blockData.x, blockData.y));
                        break;
                    case "GlassBlock":
                        level.blocks.add(new GlassBlock(blockData.x, blockData.y));
                        break;
                    case "SteelBlock":
                        level.blocks.add(new SteelBlock(blockData.x, blockData.y));
                        break;
                }
            }

            level.pigs.clear();
            level.pigs.addAll(this.pigPositions);
        }

        private static class BlockData implements Serializable {
            String blockType;
            float x, y;

            BlockData(String blockType, float x, float y) {
                this.blockType = blockType;
                this.x =x;
                this.y = y;
            }
        }
    }
}
