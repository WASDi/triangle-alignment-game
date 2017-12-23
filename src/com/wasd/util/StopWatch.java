package com.wasd.util;

public class StopWatch {

    private long tick;

    public void reset() {
        tick = System.nanoTime();
    }

    public void time(String info) {
        long now = System.nanoTime();
        long timeSinceTick = now - tick;

        System.out.printf("%20s: %7.2fms\n", info, timeSinceTick / 1e6d);

        tick = now;
    }
}
