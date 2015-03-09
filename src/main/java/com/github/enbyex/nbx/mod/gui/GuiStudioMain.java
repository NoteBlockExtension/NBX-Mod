package com.github.enbyex.nbx.mod.gui;

import com.github.enbyex.nbx.mod.handler.StudioHandler;
import com.github.enbyex.nbx.studio.NBXStudio;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author soniex2
 */
public class GuiStudioMain extends GuiStudio {

    // this can get slow if the user is crazy and opens more than 100 windows
    public List<GuiElementWindow> windowList = new CopyOnWriteArrayList<GuiElementWindow>();
    private GuiScreen background;
    private StudioHandler handler;
    // the studio gives us access to the studio adapter.
    private NBXStudio studio;
    private boolean handlerOldSkip;
    private GuiElementWindow selected = null;
    private GuiButton closeButton;

    public GuiStudioMain(GuiScreen background, StudioHandler handler) {
        this.background = background;
        this.handler = handler;
        this.handlerOldSkip = handler.skipPostInitCheck;
        handler.skipPostInitCheck = true;
        // add main window
        windowList.add(selected = new GuiElementStudioWindow(this));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                exit();
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        boolean b = false;
        if (background != null) {
            mc.currentScreen = background;
            b = background.doesGuiPauseGame();
            mc.currentScreen = this;
        }

        return b;
    }

    public void drawCenteredString(FontRenderer fr, String s, int x, int y, int c, boolean v) {
        fr.drawStringWithShadow(s, x - fr.getStringWidth(s) / 2, v ? y - (fontRendererObj.FONT_HEIGHT / 2) : y, c);
    }

    public void drawCenteredStringNoShadow(FontRenderer fr, String s, int x, int y, int c, boolean v) {
        fr.drawString(s, x - fr.getStringWidth(s) / 2, v ? y - (fontRendererObj.FONT_HEIGHT / 2) : y, c);
    }

    /**
     * Draws a rectangle with a vertical gradient between the specified colors.
     */
    @Override
    public void drawGradientRect(int p_73733_1_, int p_73733_2_, int p_73733_3_, int p_73733_4_, int p_73733_5_, int p_73733_6_) {
        super.drawGradientRect(p_73733_1_, p_73733_2_, p_73733_3_, p_73733_4_, p_73733_5_, p_73733_6_);
    }

    @Override
    public void drawHorizontalLine(int p_73730_1_, int p_73730_2_, int p_73730_3_, int p_73730_4_) {
        super.drawHorizontalLine(p_73730_1_, p_73730_2_, p_73730_3_, p_73730_4_);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
        if (background != null) {
            mc.currentScreen = background;
            background.drawScreen(0, 0, renderPartialTicks);
            mc.currentScreen = this;
        }

        GL11.glPushMatrix();

        // Change GUI scaling
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glLoadIdentity();
        // TODO implement own GUI scale
        GL11.glOrtho(0.0D, (double)mc.displayWidth, (double)mc.displayHeight, 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
        GL11.glLineWidth(1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);

        for (GuiElementWindow w : windowList) {
            w.drawBackground(mouseX, mouseY, renderPartialTicks);
            w.drawForeground(mouseX, mouseY, renderPartialTicks);
        }

        if (this.mc.thePlayer != null && this.mc.thePlayer.openContainer != null) {
            GL11.glEnable(GL11.GL_LIGHTING);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void drawVerticalLine(int p_73728_1_, int p_73728_2_, int p_73728_3_, int p_73728_4_) {
        super.drawVerticalLine(p_73728_1_, p_73728_2_, p_73728_3_, p_73728_4_);
    }

//    protected void checkMove(int mouseX, int mouseY, int button) {
//        if (button != 0) return;
//        if (mouseX < posX || mouseX > posX + sizeX) return;
//        if (mouseY < posY || mouseY > posY + titlebar_height) return;
//        moving = !moving;
//    }

    @Override
    public void drawWorldBackground(int p_146270_1_) {
        // we don't draw a background
        if (background != null) {
            mc.currentScreen = background;
            background.drawWorldBackground(p_146270_1_);
            mc.currentScreen = this;
        }
    }

//    protected void drawBackground(int mouseX, int mouseY, float renderPartialTicks) {
//        int border = 0x222222;
//        this.drawGradientRect(posX - 1, posY - 1, posX + sizeX + 1, posY + sizeY + 1, 0xFF000000 | border, 0xFF000000 | border);
//        int main_window_top = 0xEEFFEE;
//        int main_window_bottom = 0xFFEEEE;
//        this.drawGradientRect(posX, posY, posX + sizeX, posY + sizeY, 0xFF000000 | main_window_top, 0xFF000000 | main_window_bottom);
//
//        // titlebar
//        int tb_top = 0xFFDDDD;
//        int tb_bottom = 0xDDFFDD;
//        this.drawGradientRect(posX, posY, posX + sizeX, posY + titlebar_height, 0xFF000000 | tb_top, 0xFF000000 | tb_bottom);
//    }

    private void exit() {
        handler.skipPostInitCheck = handlerOldSkip;
        mc.displayGuiScreen(background);
        if (mc.currentScreen == this) {
            handler.skipPostInitCheck = true;
        }
    }

    public GuiElementWindow getSelected() {
        return selected;
    }

//    protected void drawForeground(int mouseX, int mouseY, float renderPartialTicks) {
//        // title
//        drawCenteredStringNoShadow(fontRendererObj, "NBX Studio", posX + (sizeX / 2), posY + 10, 0xFF000000, true);
//        drawCenteredStringNoShadow(fontRendererObj, "It works! Well kinda...", posX + (sizeX / 2), posY + (sizeY / 2), 0xFF800080, true);
//    }

    public void setSelected(GuiElementWindow selected) {
        // remove the new selection
        windowList.remove(selected);
        // put it in front
        windowList.add(selected);
        this.selected = selected;
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        // Scale mouse pos, based on MC's GUI scale.
        int mcMouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int mcMouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;

        // TODO scale mouse pos based on other stuff

//        if (moving) {
//            posX = mouseX - (sizeX / 2);
//            posY = mouseY - (titlebar_height / 2);
//            // TODO better moving system
//            closeButton.xPosition = posX + sizeX - closeButton.getButtonWidth();
//            closeButton.yPosition = posY;
//        }
        for (GuiElementWindow w : windowList) {
            w.handleMove(mcMouseX, mcMouseY);
        }
    }

    @Override
    public void initGui() {
        closeButton = new GuiButton(0, 0, 0, 20, 20, "x");
        buttonList.add(closeButton);
    }

    @Override
    protected void keyTyped(char character, int keycode) {
        for (GuiElementWindow w : windowList) {
            if (w.keyTyped(character, keycode))
                return;
        }
        if (background != null && keycode == 1) {
            exit();
        } else {
            super.keyTyped(character, keycode);
        }
        if (mc.currentScreen == this) {
            handler.skipPostInitCheck = true;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        for (GuiElementWindow w : windowList) {
            w.mouseClicked(mouseX, mouseY, button);
        }
//        checkMove(mouseX, mouseY, button);
    }

    @Override
    public void onGuiClosed() {
        handler.skipPostInitCheck = handlerOldSkip;
        for (GuiElementWindow w : windowList) {
            w.onGuiClosed();
        }
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int xres, int yres) {
        if (background != null) {
            mc.currentScreen = background;
            background.setWorldAndResolution(mc, xres, yres);
            mc.currentScreen = this;
        }

        super.setWorldAndResolution(mc, xres, yres);
    }

    @Override
    public void updateScreen() {
        if (background != null) {
            mc.currentScreen = background;
            background.updateScreen();
            mc.currentScreen = this;
        }

        super.updateScreen();
    }
}
