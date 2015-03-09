package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.ISound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

/**
 * @author soniex2
 */
public class MCSound implements ISound {

    private final ResourceLocation resource;
    private Minecraft mc;

    public MCSound(Minecraft mc, String name) {
        this.resource = new ResourceLocation(name);
        this.mc = mc;
    }

    @Override
    public String getName() {
        return resource.getResourceDomain() + ":" + resource.getResourcePath();
    }

    @Override
    public void play(float pitch) {
        // TODO?
        mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(resource, pitch));
    }
}
