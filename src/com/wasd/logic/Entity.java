package com.wasd.logic;

import com.wasd.logic.functions.CompleteTriangleFunction;
import com.wasd.logic.functions.StepTowardsPosition;
import com.wasd.util.Float2;

public class Entity {

    private final Float2 position;
    private Float2 nextPosition;

    private Entity target1;
    private Entity target2;

    public Entity(float x, float y) {
        this.position = new Float2(x, y);
    }

    public Float2 desiredPosition() {
        return CompleteTriangleFunction.apply(this);
    }

    public void calculateFutureStep(float stepSize) {
        nextPosition = StepTowardsPosition.step(position, desiredPosition(), stepSize);
    }

    public void applyFutureStep() {
        position.set(nextPosition);
    }

    public void setTarget1(Entity target1) {
        this.target1 = target1;
    }

    public void setTarget2(Entity target2) {
        this.target2 = target2;
    }

    public Entity getTarget1() {
        return target1;
    }

    public Entity getTarget2() {
        return target2;
    }

    public Float2 getPosition() {
        return position;
    }

    public boolean hasTarget(Entity entity) {
        return entity == target1 || entity == target2;
    }
}
