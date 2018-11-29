package com.academiadecodigo.hashtronauts.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;

public class Utils {
    private static GlyphLayout glyphLayout = new GlyphLayout();


    /**
     * Creates a {@link Vector2} containing the width and height
     *
     * @param font the bitmap used
     * @param text the text showed
     * @return vector2 with the width and height (x, y)
     */
    public static Vector2 getStringSize(BitmapFont font, String text) {

        glyphLayout.setText(font, text);

        return new Vector2(glyphLayout.width, glyphLayout.height);
    }
}
