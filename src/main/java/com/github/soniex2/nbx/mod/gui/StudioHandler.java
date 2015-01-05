package com.github.soniex2.nbx.mod.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author soniex2
 */
public class StudioHandler {

    public GuiButton myButton;

    public boolean drawStuff = false;

    private HashMap<Integer, Boolean> map;
    private int lastButtonId;
    private HashMap<GuiButton, Integer> buttonMap;

    public StudioHandler() {
        buttonMap = new HashMap<GuiButton, Integer>();
    }

    private int getFreeId(List buttonList) {
        if (map == null)
            map = new HashMap<Integer, Boolean>();
        return getFreeId(map, buttonList);
    }

    private int getFreeId(HashMap<Integer, Boolean> map, List buttonList) {
        return lastButtonId = getFreeId(map, buttonList, lastButtonId);
    }

    private int getFreeId(HashMap<Integer, Boolean> map, List buttonList, int start) {
        Iterator iter = buttonList.iterator();
        while (iter.hasNext()) {
            GuiButton button = (GuiButton) iter.next();
            map.put(button.id, true);
        }
        int ourId;
        for (ourId = start; map.containsKey(ourId); ourId++) {
        }
        return ourId;
    }

    @SubscribeEvent
    public void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        buttonMap.clear();
        myButton = new GuiButton(getFreeId(event.buttonList), 0, 0, 20, 20, "x");
        event.buttonList.add(myButton);
        buttonMap.put(myButton, myButton.id);

        if (drawStuff) {
            System.out.println("We're initting");
            for (Object o : event.buttonList) {
                GuiButton b = (GuiButton) o;
                if (!buttonMap.containsKey(b)) {
                    b.enabled = false; // disable all buttons
                }
            }
        }
    }

    @SubscribeEvent
    public void onGuiPostDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (drawStuff) {
            // this is where we draw stuff.
            // TODO draw black/grey transparent background over GUI
            event.gui.mc.fontRenderer.drawString("Minecraft NBX Studio Mod is coming!", 64, 64, 0xAA0000, true);
        }
    }

    @SubscribeEvent
    public void onButton(GuiScreenEvent.ActionPerformedEvent.Pre event) {
        if (event.button.equals(myButton)) {
            drawStuff = !drawStuff;
            Minecraft.getMinecraft().displayGuiScreen(Minecraft.getMinecraft().currentScreen);
            event.setCanceled(true); // avoid mods shouting at us and stuff
        }
        if (drawStuff && !buttonMap.containsKey(event.button)) {
            event.setCanceled(true);
        }
    }
}
