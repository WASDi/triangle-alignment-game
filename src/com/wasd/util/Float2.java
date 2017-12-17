package com.wasd.util;

public class Float2 {

    public float x;
    public float y;

    public Float2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("[%.2f, %.2f]", x, y);
    }

    public float distance(Float2 other) {
        float deltaX = other.x - x;
        float deltaY = other.y - y;
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public void set(Float2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Float2 between(Float2 other) {
        return new Float2((this.x + other.x) / 2f,
                          (this.y + other.y) / 2f);
    }

    public Float2 minus(Float2 other) {
        return new Float2(this.x - other.x,
                          this.y - other.y);
    }

    public Float2 plus(Float2 other) {
        return new Float2(this.x + other.x,
                          this.y + other.y);
    }

    public Float2 multiplyMutate(float v) {
        this.x *= v;
        this.y *= v;
        return this;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Float2 normalizeMutate() {
        float lengthInv = 1 / length();
        this.x *= lengthInv;
        this.y *= lengthInv;
        return this;
    }
}
