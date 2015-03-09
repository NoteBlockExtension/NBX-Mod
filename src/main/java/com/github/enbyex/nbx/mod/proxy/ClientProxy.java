package com.github.enbyex.nbx.mod.proxy;

import com.github.enbyex.nbx.mod.handler.StudioHandler;
import com.github.enbyex.nbx.mod.studioimpl.MCStudioAdapter;
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
