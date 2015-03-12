package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.IOptionsAdapter;
import com.github.enbyex.nbx.studio.api.IWindow;
import com.github.enbyex.nbx.studio.api.IWindowAdapter;
import net.minecraft.client.Minecraft;

/**
 * @author soniex2
 */
public class MCWindowAdapter implements IWindowAdapter {
    private Minecraft mc;

    public MCWindowAdapter(Minecraft mc) {
        this.mc = mc;
    }

    @Override
    public IWindow newWindow(int width, int height, boolean visible) {
        return new MCWindow(mc, width, height, visible);
    }
}
