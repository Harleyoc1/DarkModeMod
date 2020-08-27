package com.harleyoconnor.darkmode;

import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DarkModeEvents {

    @SubscribeEvent
    public void onGuiOpen (GuiContainerEvent event) {
        // This will be our way of detecting events, where we can replace light-mode GUIs with darks if dark mode is enabled.
    }

}
