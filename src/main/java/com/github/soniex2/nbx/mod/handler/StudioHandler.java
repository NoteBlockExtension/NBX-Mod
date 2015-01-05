package com.github.soniex2.nbx.mod.handler;

import com.github.soniex2.nbx.mod.gui.GuiStudio;
import com.github.soniex2.nbx.mod.gui.GuiStudioMain;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author soniex2
 */
public class StudioHandler {

    public GuiButton myButton;

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
        // Don't run for our GUIs
        if (event.gui instanceof GuiStudio) return;

        buttonMap.clear();
        myButton = new GuiButton(getFreeId(event.buttonList), 0, 0, 20, 20, "x");
        event.buttonList.add(myButton);
        buttonMap.put(myButton, myButton.id);
    }

    @SubscribeEvent
    public void onButton(GuiScreenEvent.ActionPerformedEvent.Pre event) {
        if (event.button.equals(myButton)) {
            // HACK! might have issues with OptiFine!
            // remove myButton
            myButton.visible = false;
            myButton.enabled = false;
            // store old screen (background)
            GuiScreen old = Minecraft.getMinecraft().currentScreen;
            // set current screen to null
            Minecraft.getMinecraft().currentScreen = null;
            // start up new screen, bypassing old screen onGuiClosed
            Minecraft.getMinecraft().displayGuiScreen(new GuiStudioMain(old));

            event.setCanceled(true); // avoid mods shouting at us and stuff
        }
    }
}
