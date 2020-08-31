package com.harleyoconnor.darkmode;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Mod("darkmode")
public class DarkMode {

    // Enum for storing all supported operating systems.
    public enum OS {
        WINDOWS, MAC, UNSUPPORTED
    }

    // Get instance of logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // Store instance of operating system as set in constructor.
    private static OS OPERATING_SYSTEM;

    public DarkMode() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));

        final String osName = System.getProperty("os.name");
        LOGGER.info("Detected Operating System: " + osName);

        if (osName.toLowerCase().startsWith("mac")) {
            LOGGER.info("Initiating MacOS support.");
            OPERATING_SYSTEM = OS.MAC;
        } else if (osName.toLowerCase().startsWith("windows")) {
            LOGGER.info("Initiating Windows support.");
            OPERATING_SYSTEM = OS.WINDOWS;
        } else {
            OPERATING_SYSTEM = OS.UNSUPPORTED;
        }

        if (OPERATING_SYSTEM != OS.UNSUPPORTED) LOGGER.info("Detected Current State of Dark Mode for OS: " + isDarkMode() + ".");

        MinecraftForge.EVENT_BUS.register(new DarkModeEvents());
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static OS getOperatingSystem() {
        return OPERATING_SYSTEM;
    }

    public static boolean isDarkMode() {
        if (OPERATING_SYSTEM == OS.MAC) {
            try {
                final Process proc = Runtime.getRuntime().exec(new String[] {"defaults", "read", "-g", "AppleInterfaceStyle"});
                proc.waitFor(100, TimeUnit.MILLISECONDS);
                return proc.exitValue() == 0;
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else if (OPERATING_SYSTEM == OS.WINDOWS) {
            // TODO: Find way to detect Windows operating system.
        }

        // Return false if OS is unsupported, this will change later with introduction of custom scheduling which can be independent of the OS.
        return false;
    }

}
