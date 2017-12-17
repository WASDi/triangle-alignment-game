package com.wasd.logic.functions;

import com.wasd.logic.Entity;
import com.wasd.util.Float2;

public class CompleteTriangleFunction {

    private static final float PERFECT_TRIANGLE_FACTOR = (float) (2 * Math.sqrt(0.75));

    public static Float2 apply(Entity entity) {
        Float2 leg1 = entity.getTarget1().getPosition();
        Float2 leg2 = entity.getTarget2().getPosition();
        Float2 center = leg1.between(leg2);

        Float2 leg1Rotated = rotate90(center, leg1);
        Float2 leg2Rotated = rotate90(center, leg2);

        Float2 closest;

        if (leg1Rotated.distance(entity.getPosition()) < leg2Rotated.distance(entity.getPosition())) {
            closest = leg1Rotated;
        } else {
            closest = leg2Rotated;
        }

        return multiplyFromCenter(center, closest);
    }

    private static Float2 multiplyFromCenter(Float2 center, Float2 p) {
        Float2 norm = p.minus(center);
        norm.multiplyMutate(PERFECT_TRIANGLE_FACTOR);
        return center.plus(norm);
    }

    private static Float2 rotate90(Float2 center, Float2 p) {
        Float2 norm = p.minus(center);
        return center.plus(new Float2(norm.y, -norm.x));
    }

}
