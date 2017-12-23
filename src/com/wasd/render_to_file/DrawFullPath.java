package com.wasd.render_to_file;

import com.wasd.Settings;
import com.wasd.graphics.CoordinateTranslation;
import com.wasd.logic.World;
import com.wasd.util.Int2;
import com.wasd.util.StopWatch;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.wasd.Settings.ENTITY_RENDER_SIZE;

public class DrawFullPath {

    private static final int IMAGE_SIZE = 1000;
    private static final int ENTITIES_TO_RENDER = 60;
    private static final int ITERATIONS = 10000;

    public static void main(String[] args) throws IOException {

        StopWatch stopWatch = new StopWatch();
        CoordinateTranslation coordinateTranslation = new CoordinateTranslation();
        coordinateTranslation.onResize(new Dimension(IMAGE_SIZE, IMAGE_SIZE));
        World world = new World(Settings.NUM_ENTITIES);

        List<EntityImageWrapper> entities = new ArrayList<>(ENTITIES_TO_RENDER);


        stopWatch.reset();
        for (int i = 0; i < ENTITIES_TO_RENDER; i++) {
            entities.add(EntityImageWrapper.create(world.getEntity(i), coordinateTranslation, IMAGE_SIZE));
        }
        stopWatch.time("Init wrappers");

        for (int i = 0; i < ITERATIONS; i++) {
            world.step(.2f);

            for (EntityImageWrapper entity : entities) {
                entity.render();
            }
        }
        stopWatch.time("Iterations");

        for (int i = 0; i < ENTITIES_TO_RENDER; i++) {
            EntityImageWrapper entity = entities.get(i);
            File outputfile = new File(String.format("/tmp/triangle/out_%02d.bmp", i));
            ImageIO.write(entity.getImage(), "bmp", outputfile);
        }
        stopWatch.time("Output to file");
    }

    private static void drawEntity(Graphics g, Int2 pos) {
        g.fillOval(pos.x - ENTITY_RENDER_SIZE / 2,
                   pos.y - ENTITY_RENDER_SIZE / 2,
                   ENTITY_RENDER_SIZE,
                   ENTITY_RENDER_SIZE);
    }

}
