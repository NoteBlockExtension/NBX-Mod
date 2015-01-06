package com.github.enbyex.nbx.mod.gui;

/**
 * @author soniex2
 */
public abstract class GuiElementWindow extends GuiElement {
    public void onGuiClosed() {
    }

    public boolean keyTyped(char character, int keycode) {
        if (keycode == 1 && guiStudioMain.getSelected() == this) {
            // exit GUI
            return true;
        }
        return false;
    }
}
