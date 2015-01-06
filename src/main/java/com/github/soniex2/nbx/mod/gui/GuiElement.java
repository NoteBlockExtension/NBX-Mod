package com.github.soniex2.nbx.mod.gui;

/**
 * @author soniex2
 */
public abstract class GuiElement {
    private int sizeX = 100;
    private int sizeY = 100;

    private int posX = 1;
    private int posY = 1;

    private boolean moving = false;

    public void handleMove(int mouseX, int mouseY) {
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
    }

    public void drawBackground(int mouseX, int mouseY, float renderPartialTicks) {
    }

    public void drawForeground(int mouseX, int mouseY, float renderPartialTicks) {
    }
}
