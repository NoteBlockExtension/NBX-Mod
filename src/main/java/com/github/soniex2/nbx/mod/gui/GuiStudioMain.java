package com.github.soniex2.nbx.mod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author soniex2
 */
public class GuiStudioMain extends GuiStudio {

    GuiScreen background;

    public GuiStudioMain(GuiScreen background) {
        this.background = background;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
        // HACK!
        Minecraft.getMinecraft().currentScreen = background;
        background.drawScreen(0, 0, renderPartialTicks);
        Minecraft.getMinecraft().currentScreen = this;
        // end hack

        super.drawScreen(mouseX, mouseY, renderPartialTicks);
    }

    @Override
    protected void keyTyped(char character, int keycode) {
        if (background != null && keycode == 1) {
            Minecraft.getMinecraft().currentScreen = background;
        }
        super.keyTyped(character, keycode);
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int xres, int yres) {
        // HACK!
        Minecraft.getMinecraft().currentScreen = background;
        background.setWorldAndResolution(mc, xres, yres);
        Minecraft.getMinecraft().currentScreen = this;
        // end hack

        super.setWorldAndResolution(mc, xres, yres);
    }

    @Override
    public void updateScreen() {
        // HACK!
        Minecraft.getMinecraft().currentScreen = background;
        background.updateScreen();
        Minecraft.getMinecraft().currentScreen = this;
        // end hack

        super.updateScreen();
    }

    @Override
    public void drawWorldBackground(int p_146270_1_) {
        // we don't draw a background
        // HACK!
        Minecraft.getMinecraft().currentScreen = background;
        background.drawWorldBackground(p_146270_1_);
        Minecraft.getMinecraft().currentScreen = this;
        // end hack
    }

    @Override
    public boolean doesGuiPauseGame() {
        // HACK!
        Minecraft.getMinecraft().currentScreen = background;
        boolean b = background.doesGuiPauseGame();
        Minecraft.getMinecraft().currentScreen = this;
        // end hack

        return b;
    }

}
