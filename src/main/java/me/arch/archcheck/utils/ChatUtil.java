package me.arch.archcheck.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ChatUtil {

    // Format color codes
    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text); // Returning formated text
    }

    // Execute command from (as a) console
    public static void executeConsole(String command) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }

}
