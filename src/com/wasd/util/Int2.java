package com.wasd.util;

public class Int2 {

    public int x;
    public int y;

    public Int2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Int2() {
        this.x = 0;
        this.y = 0;
    }

    public Int2(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
