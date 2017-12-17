package com.wasd;

import com.wasd.logic.World;

public class Benchmark {

    public static void main(String[] args) {
        World world = new World(1000);

        long startTime = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            world.step(.01f);
        }

        long endTime = System.nanoTime();

        System.out.printf("%.2fms", (endTime - startTime) / 1e6f);
    }

}
