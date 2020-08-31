package com.harleyoconnor.darkmode;

import net.minecraft.inventory.container.ChestContainer;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DarkModeEvents {

    @SubscribeEvent
    public void onGuiOpen (GuiContainerEvent event) {
        // This will be our way of detecting events, where we can replace light-mode GUIs with darks if dark mode is enabled.
        // After having looked into the source code, it's likely we will need to use bytecode manipulation to get the desired result of changing the background only.
        // if (event.getGuiContainer().getContainer() instanceof ChestContainer) DarkMode.getLogger().info("Chest opened.");
    }

}
