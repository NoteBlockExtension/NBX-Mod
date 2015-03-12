package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.IOptionsAdapter;
import com.github.enbyex.nbx.studio.api.ITab;
import net.minecraft.client.Minecraft;

import java.util.List;

/**
 * @author soniex2
 */
public class MCOptionsAdapter implements IOptionsAdapter {
    private final Minecraft mc;

    private MCOptionsTab mcOptTab;

    public MCOptionsAdapter(Minecraft mc) {
        this.mc = mc;
        this.mcOptTab = new MCOptionsTab(mc);
    }

    @Override
    public void addTabs(List<ITab> tabs) {
        tabs.add(mcOptTab);
    }
}
