package com.wasd.render_to_file;

import com.wasd.graphics.CoordinateTranslation;
import com.wasd.logic.Entity;
import com.wasd.util.Int2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static com.wasd.graphics.CoordinateTranslation.ENTITY_RENDER_SIZE;

public class EntityImageWrapper {

    private final BufferedImage image;
    private final Graphics2D graphics;
    private final Entity entity;
    private final CoordinateTranslation coordinateTranslation;

    private EntityImageWrapper(BufferedImage image, Graphics2D graphics, Entity entity, CoordinateTranslation coordinateTranslation) {
        this.image = image;
        this.graphics = graphics;
        this.entity = entity;
        this.coordinateTranslation = coordinateTranslation;
    }

    public void render() {
        Int2 pos = coordinateTranslation.entityToWindow(entity.getPosition());
        graphics.fillOval(pos.x - ENTITY_RENDER_SIZE / 2,
                          pos.y - ENTITY_RENDER_SIZE / 2,
                          ENTITY_RENDER_SIZE,
                          ENTITY_RENDER_SIZE);
    }

    public BufferedImage getImage() {
        return image;
    }

    public static EntityImageWrapper create(Entity entity, CoordinateTranslation coordinateTranslation, int imageSize) {
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.RED);
        return new EntityImageWrapper(image, graphics, entity, coordinateTranslation);
    }
}
