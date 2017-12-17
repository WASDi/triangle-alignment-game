package com.wasd.logic;

import com.wasd.logic.functions.AssignDistinctTargets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    public static final int NUM_ENTITIES = 8;

    private static final int SUBSTEPS = 10;

    private final List<Entity> entities = new ArrayList<>();
    private final int numEntities;

    public World(int numEntities) {
        this.numEntities = numEntities;
        regenerate();
    }

    public void regenerate() {
        Random r = new Random();
        entities.clear();

        for (int i = 0; i < numEntities; i++) {
            entities.add(new Entity(r.nextFloat(), r.nextFloat()));
        }
        AssignDistinctTargets.apply(r, entities);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void step(float stepSize) {

        stepSize = stepSize / SUBSTEPS;

        for (int i = 0; i < SUBSTEPS; i++) {
            for (Entity entity : entities) {
                entity.calculateFutureStep(stepSize);
            }
            for (Entity entity : entities) {
                entity.applyFutureStep();
            }
        }

    }
}
