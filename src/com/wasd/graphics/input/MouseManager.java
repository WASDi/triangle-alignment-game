package com.wasd.graphics.input;

import com.wasd.graphics.CoordinateTranslation;
import com.wasd.logic.Entity;
import com.wasd.logic.World;
import com.wasd.util.Int2;

import java.awt.event.MouseEvent;

public class MouseManager {

    private final World world;
    private final CoordinateTranslation coordinateTranslation;
    private final Runnable repaintFunction;

    private final Int2 mousePos = new Int2();

    private Entity selected;
    private boolean mouseDown;

    public MouseManager(World world, CoordinateTranslation coordinateTranslation, Runnable repaintFunction) {
        this.world = world;
        this.coordinateTranslation = coordinateTranslation;
        this.repaintFunction = repaintFunction;
    }

    public void press(MouseEvent e) {
        selected = PickFunction.pick(e, coordinateTranslation, world);
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
        }
        repaintFunction.run();
    }

    public void release(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = false;
        }
        repaintFunction.run();
    }

    public void move(MouseEvent e) {
        mousePos.set(e.getX(), e.getY());
        if (mouseDown && selected != null) {
            selected.getPosition().set(coordinateTranslation.windowToEntity(mousePos));
        }
        repaintFunction.run();
    }

    public Int2 getMousePos() {
        return mousePos;
    }

    public Entity getSelected() {
        return selected;
    }
}
