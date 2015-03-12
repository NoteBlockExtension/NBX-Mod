package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.ITab;
import net.minecraft.client.Minecraft;

/**
 * @author soniex2
 */
public abstract class MCTab implements ITab {
    private final Minecraft mc;
    private final String name;

    public MCTab(Minecraft mc, String name) {
        this.mc = mc;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
