package com.github.enbyex.nbx.mod.gui;

/**
 * @author soniex2
 */
public abstract class GuiElement {
    public GuiStudioMain guiStudioMain;
    public int sizeX = 100;
    public int sizeY = 100;
    public int posX = 1;
    public int posY = 1;
    private boolean moving = false;

    public GuiElement(GuiStudioMain guiStudioMain) {
        this.guiStudioMain = guiStudioMain;
    }

    public abstract void drawBackground(int mouseX, int mouseY, float renderPartialTicks);

    public abstract void drawForeground(int mouseX, int mouseY, float renderPartialTicks);

    public void handleMove(int mouseX, int mouseY) {
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
    }
}
