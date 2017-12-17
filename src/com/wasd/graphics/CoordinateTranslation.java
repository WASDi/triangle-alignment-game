package com.wasd.graphics;

import com.wasd.util.Float2;
import com.wasd.util.Int2;

import java.awt.Dimension;

public class CoordinateTranslation {

    public static final int RENDER_SCALE = 200;
    public static final int ENTITY_RENDER_SIZE = 7;
    public static final float PICK_SIZE = (0.5f * (ENTITY_RENDER_SIZE + 10)) / RENDER_SCALE;

    private float centerX;
    private float centerY;
    private int height;

    public Int2 entityToWindow(Float2 pos) {
        float x = centerX + pos.x * RENDER_SCALE - ENTITY_RENDER_SIZE / 2f - RENDER_SCALE / 2f;
        float y = centerY + pos.y * RENDER_SCALE - ENTITY_RENDER_SIZE / 2f - RENDER_SCALE / 2f;

        return new Int2(x, height - y);
    }

    public Float2 windowToEntity(Int2 windowPos) {
        return windowToEntity(windowPos.x, windowPos.y);
    }

    public Float2 windowToEntity(int windowX, int windowY) {
        float entityX = (RENDER_SCALE / 2f + ENTITY_RENDER_SIZE / 2f - centerX + windowX) / RENDER_SCALE;
        float entityY = (RENDER_SCALE / 2f + ENTITY_RENDER_SIZE / 2f - centerY - windowY + height) / RENDER_SCALE;

        return new Float2(entityX, entityY);
    }

    public void onResize(Dimension newSize) {
        centerX = (float) (newSize.getWidth() / 2f);
        centerY = (float) (newSize.getHeight() / 2f);
        height = (int) newSize.getHeight();
    }
}
