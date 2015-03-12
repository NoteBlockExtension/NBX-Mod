package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.*;
import net.minecraft.client.Minecraft;

/**
 * @author soniex2
 */
public class MCStudioAdapter implements IStudioAdapter {

    private Minecraft mc;
    private MCSoundAdapter soundAdapter;
    private MCWindowAdapter windowAdapter;
    private MCOptionsAdapter optionsAdapter;
    private MCEventAdapter eventAdapter;

    public MCStudioAdapter(Minecraft mc) {
        this.mc = mc;
        this.soundAdapter = new MCSoundAdapter(mc);
        this.windowAdapter = new MCWindowAdapter(mc);
        this.optionsAdapter = new MCOptionsAdapter(mc);
        this.eventAdapter = new MCEventAdapter(mc);
    }

    @Override
    public ISoundAdapter getSoundAdapter() {
        return soundAdapter;
    }

    @Override
    public IWindowAdapter getWindowAdapter() {
        return windowAdapter;
    }

    @Override
    public IOptionsAdapter getOptionsAdapter() {
        return optionsAdapter;
    }

    @Override
    public IEventAdapter getEventAdapter() {
        return eventAdapter;
    }
}
