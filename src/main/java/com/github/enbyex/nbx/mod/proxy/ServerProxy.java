package com.github.enbyex.nbx.mod.proxy;

/**
 * @author soniex2
 */
public class ServerProxy extends CommonProxy {
    @Override
    public void preInit() {
        throw new IllegalStateException("NBX Mod is client side. (for now)");
    }
}
