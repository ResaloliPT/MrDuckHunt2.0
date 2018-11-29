package com.academiadecodigo.hashtronauts.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public enum Fonts {
    COLLEGE("fonts/college.ttf", 30);

    private String fontPath;
    private int size;

    Fonts(String fontPath, int size) {
        this.fontPath = fontPath;
        this.size = size;
    }

    public static BitmapFont getCustomFont(String ttfPath, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(ttfPath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

    public BitmapFont getFont() {
        return getCustomFont(fontPath, size);
    }
}
