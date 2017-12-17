package com.wasd.graphics.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseWrapper implements MouseListener, MouseMotionListener {

    private final MouseManager mouseManager;

    public MouseWrapper(MouseManager mouseManager) {
        this.mouseManager = mouseManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseManager.press(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseManager.release(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseManager.move(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseManager.move(e);
    }
}
