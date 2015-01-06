package com.github.enbyex.nbx.mod;

import com.github.enbyex.nbx.mod.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author soniex2
 */
@Mod(name = NBXMod.MODNAME, modid = NBXMod.MODID, version = NBXMod.VERSION, dependencies = "required-after:OpenComputers")
public class NBXMod {
    /* Constants */
    public static final String MODNAME = "NBXMod";
    public static final String MODID = "nbxmod";
    public static final String VERSION = "1.0";
    public static final String PKGBASE = "com.github.enbyex.nbx.mod";
    public static final String PROXYBASE = PKGBASE + ".proxy";

    @Mod.Instance
    public static NBXMod instance;

    @SidedProxy(clientSide = PROXYBASE + ".ClientProxy", serverSide = PROXYBASE + ".ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
