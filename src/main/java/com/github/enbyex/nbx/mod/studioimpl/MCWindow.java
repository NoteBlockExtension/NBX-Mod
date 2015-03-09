package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.IWindow;
import net.minecraft.client.Minecraft;

/**
 * @author soniex2
 */
public class MCWindow implements IWindow {
    private int width, height;
    private boolean isVisible;
    private Minecraft mc;

    public MCWindow(Minecraft mc, int width, int height, boolean isVisible) {
        this.width = width;
        this.height = height;
        this.isVisible = isVisible;
        this.mc = mc;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }
}
