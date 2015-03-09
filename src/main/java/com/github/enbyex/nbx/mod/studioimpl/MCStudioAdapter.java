package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.ISoundAdapter;
import com.github.enbyex.nbx.studio.api.IStudioAdapter;
import com.github.enbyex.nbx.studio.api.IWindowAdapter;
import net.minecraft.client.Minecraft;

/**
 * @author soniex2
 */
public class MCStudioAdapter implements IStudioAdapter {

    private Minecraft mc;
    private MCSoundAdapter soundAdapter;
    private MCWindowAdapter windowAdapter;

    public MCStudioAdapter(Minecraft mc) {
        this.mc = mc;
        this.soundAdapter = new MCSoundAdapter(mc);
        this.windowAdapter = new MCWindowAdapter(mc);
    }

    @Override
    public ISoundAdapter getSoundAdapter() {
        return soundAdapter;
    }

    @Override
    public IWindowAdapter getWindowAdapter() {
        return windowAdapter;
    }
}
