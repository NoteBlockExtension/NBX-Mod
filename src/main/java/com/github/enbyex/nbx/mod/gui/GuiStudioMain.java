package com.github.enbyex.nbx.mod.gui;

import com.github.enbyex.nbx.mod.handler.StudioHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author soniex2
 */
public class GuiStudioMain extends GuiStudio {

    private GuiScreen background;
    private StudioHandler handler;
    private boolean handlerOldSkip;

    // this can get slow if the user is crazy and opens more than 100 windows
    public List<GuiElementWindow> windowList = new CopyOnWriteArrayList<GuiElementWindow>();

    private GuiElementWindow selected = null;

    public void setSelected(GuiElementWindow selected) {
        // remove the new selection
        windowList.remove(selected);
        // put it in front
        windowList.add(selected);
        this.selected = selected;
    }

    public GuiStudioMain(GuiScreen background, StudioHandler handler) {
        this.background = background;
        this.handler = handler;
        this.handlerOldSkip = handler.skipPostInitCheck;
        handler.skipPostInitCheck = true;
        // add main window
        windowList.add(selected = new GuiElementStudioWindow());
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;

//        if (moving) {
//            posX = mouseX - (sizeX / 2);
//            posY = mouseY - (titlebar_height / 2);
//            // TODO better moving system
//            closeButton.xPosition = posX + sizeX - closeButton.getButtonWidth();
//            closeButton.yPosition = posY;
//        }
        for (GuiElementWindow w : windowList) {
            w.handleMove(mouseX, mouseY);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
//        switch (button.id) {
//            case 0:
//                exit();
//                break;
//        }
    }

    private void exit() {
        handler.skipPostInitCheck = handlerOldSkip;
        mc.displayGuiScreen(background);
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

//    protected void checkMove(int mouseX, int mouseY, int button) {
//        if (button != 0) return;
//        if (mouseX < posX || mouseX > posX + sizeX) return;
//        if (mouseY < posY || mouseY > posY + titlebar_height) return;
//        moving = !moving;
//    }

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

        // render ON TOP
        GL11.glTranslatef(0f, 0f, 1000f);

        for (GuiElementWindow w : windowList) {
            w.drawBackground(mouseX, mouseY, renderPartialTicks);
            w.drawForeground(mouseX, mouseY, renderPartialTicks);
        }

        if (this.mc.thePlayer != null && this.mc.thePlayer.openContainer != null) {
            GL11.glEnable(GL11.GL_LIGHTING);
        }
        GL11.glPopMatrix();
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

    protected void drawCenteredStringNoShadow(FontRenderer fr, String s, int x, int y, int c, boolean v) {
        fr.drawString(s, x - fr.getStringWidth(s) / 2, v ? y - (fontRendererObj.FONT_HEIGHT / 2) : y, c);
    }

    protected void drawCenteredString(FontRenderer fr, String s, int x, int y, int c, boolean v) {
        fr.drawStringWithShadow(s, x - fr.getStringWidth(s) / 2, v ? y - (fontRendererObj.FONT_HEIGHT / 2) : y, c);
    }

//    protected void drawForeground(int mouseX, int mouseY, float renderPartialTicks) {
//        // title
//        drawCenteredStringNoShadow(fontRendererObj, "NBX Studio", posX + (sizeX / 2), posY + 10, 0xFF000000, true);
//        drawCenteredStringNoShadow(fontRendererObj, "It works! Well kinda...", posX + (sizeX / 2), posY + (sizeY / 2), 0xFF800080, true);
//    }

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
    public void initGui() {
//        closeButton = new GuiButton(0, posX + sizeX - 20, posY, 20, 20, "x");
//        buttonList.add(closeButton);
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
        for (GuiElementWindow w : windowList) {
            w.onGuiClosed();
        }
    }

    public GuiElementWindow getSelected() {
        return selected;
    }
}
