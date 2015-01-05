package com.github.soniex2.nbx.mod.proxy;

import com.github.soniex2.nbx.mod.gui.StudioHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author soniex2
 */
public class ClientProxy extends CommonProxy {

    public static StudioHandler studioHandler;

    @Override
    public void preInit() {
        studioHandler = new StudioHandler();
        MinecraftForge.EVENT_BUS.register(studioHandler);
    }
}
