package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.IEvent;
import com.github.enbyex.nbx.studio.api.IEventAdapter;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author soniex2
 */
public class MCEventAdapter implements IEventAdapter {
    private final Minecraft mc;

    public MCEventAdapter(Minecraft mc) {
        this.mc = mc;
    }

    @Override
    public boolean post(IEvent event) {
        return MinecraftForge.EVENT_BUS.post(new EventWrapper(event));
    }

    private class EventWrapper extends Event {
        private final IEvent event;

        public EventWrapper(IEvent event) {
            this.event = event;
        }
    }
}
