package me.arch.archcheck.managers;

import me.arch.archcheck.ArchCheck;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private static final FileConfiguration config = ArchCheck.getInstance().getConfig();

    // Get Strings from config
    // Get String without default argument
    public static String getString(String path) { return config.getString(path); }
    // Get String with default argument
    public static String getString(String path, String def) { return config.getString(path, def); }

    // Get int from config
    public static int getInt(String path) { return config.getInt(path); }
    public static int getInt(String path, int def) { return config.getInt(path, def); }

    // Get booleans from config
    public static boolean getBool(String path) { return config.getBoolean(path); }
    public static boolean getBool(String path, boolean def) { return config.getBoolean(path, def); }

}
