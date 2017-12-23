package com.wasd.graphics;

import com.wasd.util.Float2;
import com.wasd.util.Int2;

import java.awt.Dimension;

import static com.wasd.Settings.ENTITY_RENDER_SIZE;
import static com.wasd.Settings.RENDER_SCALE;

public class CoordinateTranslation {

    private float centerX;
    private float centerY;
    private int height;

    public Int2 entityToWindow(Float2 pos) {
        float x = centerX + pos.x * RENDER_SCALE - ENTITY_RENDER_SIZE / 2f;
        float y = centerY + pos.y * RENDER_SCALE - ENTITY_RENDER_SIZE / 2f;

        return new Int2(x, height - y);
    }

    public Float2 windowToEntity(Int2 windowPos) {
        return windowToEntity(windowPos.x, windowPos.y);
    }

    public Float2 windowToEntity(int windowX, int windowY) {
        float entityX = (ENTITY_RENDER_SIZE / 2f - centerX + windowX) / RENDER_SCALE;
        float entityY = (ENTITY_RENDER_SIZE / 2f - centerY - windowY + height) / RENDER_SCALE;

        return new Float2(entityX, entityY);
    }

    public void onResize(Dimension newSize) {
        centerX = (float) (newSize.getWidth() / 2f);
        centerY = (float) (newSize.getHeight() / 2f);
        height = (int) newSize.getHeight();
    }
}
