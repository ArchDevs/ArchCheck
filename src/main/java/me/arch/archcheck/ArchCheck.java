package me.arch.archcheck;

import lombok.Getter;
import me.arch.archcheck.commands.CommandManager;
import me.arch.archcheck.listeners.PlayerChatListener;
import me.arch.archcheck.listeners.PlayerItemListener;
import me.arch.archcheck.listeners.PlayerLeaveListener;
import me.arch.archcheck.listeners.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public final class ArchCheck extends JavaPlugin {

    @Getter // First UUID is for player on check (target), second UUID is for player who is checking
    private static final HashMap<UUID, UUID> playersOnCheck = new HashMap<>();

    @Getter
    private final Logger logger = Bukkit.getLogger();

    @Getter // Lombok impl for getting instance
    private static ArchCheck instance; // Var for fast class access

    @Override
    public void onEnable() {
        instance = this; // Initializing var instance as an ArchCheck class;
        saveDefaultConfig();
        getConfig().options().copyDefaults();

        // Load commands
        getCommand("archcheck").setExecutor(new CommandManager());
        // Load Listeners
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
    }

    @Override
    public void onDisable() {


        instance = null;
    }

}
