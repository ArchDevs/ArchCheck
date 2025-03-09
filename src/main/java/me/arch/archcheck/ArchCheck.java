package me.arch.archcheck;

import lombok.Getter;
import me.arch.archcheck.commands.CheckTabCompleter;
import me.arch.archcheck.commands.CommandManager;
import me.arch.archcheck.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class ArchCheck extends JavaPlugin {

    @Getter // First UUID is for player on check (target), second UUID is for player who is checking
    private static final HashMap<UUID, UUID> playersOnCheck = new HashMap<>();

    @Getter // UUID is for player and Long for time in milliseconds
    private static final HashMap<UUID, Long> checkTimer = new HashMap<>();

    @Getter // Lombok impl for getting instance
    private static ArchCheck instance; // Var for fast class access

    @Override
    public void onEnable() {
        instance = this; // Initializing var instance as an ArchCheck class;
        saveDefaultConfig();
        getConfig().options().copyDefaults();

        // Load commands
        getCommand("archcheck").setExecutor(new CommandManager());
        getCommand("archcheck").setTabCompleter(new CheckTabCompleter());

        // Load Listeners
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockListener(), this);
    }

    @Override
    public void onDisable() {

        instance = null;
    }

}
