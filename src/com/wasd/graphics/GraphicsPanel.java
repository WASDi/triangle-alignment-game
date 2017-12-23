package com.wasd.graphics;

import com.wasd.graphics.input.MouseManager;
import com.wasd.graphics.input.MouseWrapper;
import com.wasd.logic.Entity;
import com.wasd.logic.World;
import com.wasd.util.Int2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static com.wasd.Settings.ENTITY_RENDER_SIZE;

public class GraphicsPanel extends JPanel {

    private final World world;
    private final CoordinateTranslation coordinateTranslation = new CoordinateTranslation();
    private final MouseManager mouseManager;

    public GraphicsPanel(World world) {
        this.world = world;
        this.mouseManager = new MouseManager(world, coordinateTranslation, GraphicsPanel.this::repaint);

        MouseWrapper mouseWrapper = new MouseWrapper(mouseManager);
        addMouseListener(mouseWrapper);
        addMouseMotionListener(mouseWrapper);
        addComponentListener(new ResizeListener());
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Entity entity : world.getEntities()) {
            drawEntity(g, entity);
        }

        Entity selected = mouseManager.getSelected();
        if (selected != null) {
            g.setColor(Color.MAGENTA);
            Int2 desiredPos = coordinateTranslation.entityToWindow(selected.desiredPosition());
            g.drawOval(desiredPos.x - 5, desiredPos.y - 5, 10, 10);
        }
    }

    private void drawEntity(Graphics g, Entity entity) {
        Int2 pos = coordinateTranslation.entityToWindow(entity.getPosition());

        Entity selected = mouseManager.getSelected();
        if (entity == selected) {
            g.setColor(Color.RED);
        } else if (selected != null && selected.hasTarget(entity)) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.BLACK);
        }

        g.fillOval(pos.x - ENTITY_RENDER_SIZE / 2,
                   pos.y - ENTITY_RENDER_SIZE / 2,
                   ENTITY_RENDER_SIZE,
                   ENTITY_RENDER_SIZE);
    }

    private class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            coordinateTranslation.onResize(getSize());
        }
    }

}
