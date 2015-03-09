package com.github.enbyex.nbx.mod.gui;

/**
 * @author soniex2
 */
public abstract class GuiElementWindow extends GuiElement {
    public GuiElementWindow(GuiStudioMain guiStudioMain) {
        super(guiStudioMain);
    }

    public void onGuiClosed() {
    }

    public boolean keyTyped(char character, int keycode) {
        return keycode == 1 && guiStudioMain.getSelected() == this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float renderPartialTicks) {
        int border = 0x222222;
        guiStudioMain.drawGradientRect(posX - 1, posY - 1, posX + sizeX + 1, posY + sizeY + 1, 0xFF000000 | border, 0xFF000000 | border);
        int main_window_top = 0xEEFFEE;
        int main_window_bottom = 0xFFEEEE;
        guiStudioMain.drawGradientRect(posX, posY, posX + sizeX, posY + sizeY, 0xFF000000 | main_window_top, 0xFF000000 | main_window_bottom);

        // titlebar
        int tb_top = 0xFFDDDD;
        int tb_bottom = 0xDDFFDD;
        guiStudioMain.drawGradientRect(posX, posY, posX + sizeX, posY + 20, 0xFF000000 | tb_top, 0xFF000000 | tb_bottom);
    }
}
