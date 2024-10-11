package me.arch.archcheck;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class ArchCheck extends JavaPlugin {

    @Getter // First UUID is for player on check (target), second UUID is for player who is checking
    private static final HashMap<UUID, UUID> playersOnCheck = new HashMap<>();

    @Getter // Lombok impl for getting instance
    private static ArchCheck instance; // Var for fast class access

    @Override
    public void onEnable() {
        instance = this; // Initializing var instance as an ArchCheck class;
    }

    @Override
    public void onDisable() {


        instance = null;
    }

}
