package com.github.soniex2.nbx.mod.gui;

import com.github.soniex2.nbx.mod.handler.StudioHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * @author soniex2
 */
public class GuiStudioMain extends GuiStudio {

    private GuiScreen background;
    private StudioHandler handler;
    private boolean handlerOldSkip;

    private int sizeX = 100;
    private int sizeY = 100;
    private int posX = 0;
    private int posY = 0;

    // should I make this final?
    private int titlebar_height = 20;

    private boolean moving = false;

    public GuiStudioMain(GuiScreen background, StudioHandler handler) {
        this.background = background;
        this.handler = handler;
        this.handlerOldSkip = handler.skipPostInitCheck;
        handler.skipPostInitCheck = true;
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;

        if (moving) {
            posX = mouseX - (sizeX / 2);
            posY = mouseY - (titlebar_height / 2);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        checkMove(mouseX, mouseY, button);
    }

    protected void checkMove(int mouseX, int mouseY, int button) {
        if (button != 0) return;
        if (mouseX < posX || mouseX > posX + sizeX) return;
        if (mouseY < posY || mouseY > posY + titlebar_height) return;
        moving = !moving;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
        // HACK!
        if (background != null) {
            mc.currentScreen = background;
            background.drawScreen(0, 0, renderPartialTicks);
            mc.currentScreen = this;
        }
        // end hack

        GL11.glPushMatrix();
        if (this.mc.thePlayer != null && this.mc.thePlayer.openContainer != null) {
            GL11.glDisable(GL11.GL_LIGHTING);
        }

        GL11.glTranslatef(1f, 1f, 1000f);

        super.drawScreen(mouseX, mouseY, renderPartialTicks);

        int border = 0x222222;
        this.drawGradientRect(posX - 1, posY - 1, posX + sizeX + 1, posY + sizeY + 1, 0xFF000000 | border, 0xFF000000 | border);
        int main_window_top = 0xEEFFEE;
        int main_window_bottom = 0xFFEEEE;
        this.drawGradientRect(posX, posY, posX + sizeX, posY + sizeY, 0xFF000000 | main_window_top, 0xFF000000 | main_window_bottom);
        int tb_top = 0xFFDDDD;
        int tb_bottom = 0xDDFFDD;
        this.drawGradientRect(posX, posY, posX + sizeX, posY + titlebar_height, 0xFF000000 | tb_top, 0xFF000000 | tb_bottom);

        // text renders under overlay :(
        this.drawCenteredString(this.fontRendererObj, "It works! Well kinda...", posX + (sizeX / 2), posY + (sizeY / 2), 0xFFFF0000);

        if (this.mc.thePlayer != null && this.mc.thePlayer.openContainer != null) {
            GL11.glEnable(GL11.GL_LIGHTING);
        }
        GL11.glPopMatrix();
    }

    @Override
    protected void keyTyped(char character, int keycode) {
        if (background != null && keycode == 1) {
            mc.currentScreen = background;
            handler.skipPostInitCheck = handlerOldSkip;
            mc.displayGuiScreen(background);
        } else {
            super.keyTyped(character, keycode);
        }
        if (mc.currentScreen == this) {
            handler.skipPostInitCheck = true;
        }
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int xres, int yres) {
        // HACK!
        if (background != null) {
            mc.currentScreen = background;
            background.setWorldAndResolution(mc, xres, yres);
            mc.currentScreen = this;
        }
        // end hack

        super.setWorldAndResolution(mc, xres, yres);
    }

    @Override
    public void updateScreen() {
        // HACK!
        if (background != null) {
            mc.currentScreen = background;
            background.updateScreen();
            mc.currentScreen = this;
        }
        // end hack

        super.updateScreen();
    }

    @Override
    public void drawWorldBackground(int p_146270_1_) {
        // we don't draw a background
        // HACK!
        if (background != null) {
            mc.currentScreen = background;
            background.drawWorldBackground(p_146270_1_);
            mc.currentScreen = this;
        }
        // end hack
    }

    @Override
    public boolean doesGuiPauseGame() {
        boolean b = false;
        // HACK!
        if (background != null) {
            mc.currentScreen = background;
            b = background.doesGuiPauseGame();
            mc.currentScreen = this;
        }
        // end hack

        return b;
    }

    @Override
    public void onGuiClosed() {
        handler.skipPostInitCheck = handlerOldSkip;
    }

}
