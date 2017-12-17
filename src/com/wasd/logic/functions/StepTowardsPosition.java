package com.wasd.logic.functions;

import com.wasd.util.Float2;

public class StepTowardsPosition {

    public static Float2 step(Float2 origin, Float2 destination, float stepSize) {
        Float2 delta = destination.minus(origin);
        if (delta.length() < stepSize) {
            return destination;
        }
        Float2 step = delta.normalizeMutate().multiplyMutate(stepSize);
        return origin.plus(step);
    }

}
