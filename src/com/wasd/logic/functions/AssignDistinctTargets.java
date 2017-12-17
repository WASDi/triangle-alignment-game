package com.wasd.logic.functions;

import com.wasd.logic.Entity;

import java.util.List;
import java.util.Random;

public class AssignDistinctTargets {

    public static void apply(Random r, List<Entity> entities) {
        for (Entity entity : entities) {
            Entity target1 = getNewTarget(entity, r, entities);
            entity.setTarget1(target1);

            Entity target2 = getNewTarget(entity, r, entities);
            entity.setTarget2(target2);
        }
    }

    private static Entity getNewTarget(Entity entity, Random r, List<Entity> entities) {
        Entity target;
        do {
            target = entities.get(r.nextInt(entities.size()));
        } while (!isValidNewTarget(entity, target));
        return target;
    }

    private static boolean isValidNewTarget(Entity entity, Entity target) {
        return entity != target && !entity.hasTarget(target);
    }

}
