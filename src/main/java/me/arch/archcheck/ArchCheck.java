package me.arch.archcheck;

import lombok.Getter;
import me.arch.archcheck.tabcompleter.CheckTabCompleter;
import me.arch.archcheck.managers.CommandManager;
import me.arch.archcheck.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;

public final class ArchCheck extends JavaPlugin {
    @Getter // Lombok impl for getting instance
    private static ArchCheck instance; // Var for fast class access

    @Override
    public void onLoad() {
        instance = this; // Initializing var instance as an ArchCheck class;
    }

    @Override
    public void onEnable() {
        System.out.println("Plugin starting...");
        long startTime = System.currentTimeMillis();

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

        System.out.println(String.format("Plugin loaded in: %s seconds", startTime - System.currentTimeMillis()));
    }

    @Override
    public void onDisable() {
        instance = null;
    }

}
