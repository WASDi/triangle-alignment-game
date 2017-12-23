package com.wasd.logic;

import com.wasd.logic.functions.AssignDistinctTargets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.wasd.Settings.SUBSTEPS;

public class World {

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
            entities.add(new Entity(r.nextFloat() - .5f,
                                    r.nextFloat() - .5f));
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

    public Entity getEntity(int i) {
        return entities.get(i);
    }
}
