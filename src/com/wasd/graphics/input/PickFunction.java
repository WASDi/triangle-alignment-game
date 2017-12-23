package com.wasd.graphics.input;

import com.wasd.Settings;
import com.wasd.graphics.CoordinateTranslation;
import com.wasd.logic.Entity;
import com.wasd.logic.World;
import com.wasd.util.Float2;

import java.awt.event.MouseEvent;

public class PickFunction {

    private static final float PICK_SIZE = (0.5f * (Settings.ENTITY_RENDER_SIZE + 10)) / Settings.RENDER_SCALE;

    public static Entity pick(MouseEvent e, CoordinateTranslation coordinateTranslation, World world) {
        Float2 mousePos = coordinateTranslation.windowToEntity(e.getX(), e.getY());

        float closestDistance = Float.POSITIVE_INFINITY;
        Entity closest = null;

        for (Entity entity : world.getEntities()) {
            float distance = mousePos.distance(entity.getPosition());
            if (distance < closestDistance && distance < PICK_SIZE) {
                closestDistance = distance;
                closest = entity;
            }
        }
        return closest;
    }
}
