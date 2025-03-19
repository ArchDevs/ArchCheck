package me.arch.archcheck.utils.config;

import lombok.experimental.FieldDefaults;
import me.arch.archcheck.managers.ConfigManager;

@FieldDefaults(makeFinal = true)
public class ConfigValues {
    // Teleport
    public static boolean teleportPlayerOnCheck = ConfigManager.getBool("teleport-player-on-check", false);
    public static String worldToTeleportPlayer = ConfigManager.getString("tp-player-on-check-pos.world", "world");
    public static int xCord = ConfigManager.getInt("tp-player-on-check-pos.x", 100);
    public static int yCord = ConfigManager.getInt("tp-player-on-check-pos.y", 100);
    public static int zCord = ConfigManager.getInt("tp-player-on-check-pos.z", 100);

    // Bossbar
    public static String bossbarTitle = ConfigManager.getString("bossbar.title", "&aCheck");
    public static String bossbarColor = ConfigManager.getString("bossbar.color", "RED");

    // Messages
    public static String noPlayer = ConfigManager.getString("messages.player-not-found", "&cPlayer not found");
    public static String noArgs = ConfigManager.getString("messages.no-arguments", "&cPlease provide some arguments to command");
    public static String configReloaded = ConfigManager.getString("messages.config-reload", "&aConfig has been reloaded");
    public static String noPerms = ConfigManager.getString("messages.no-permission", "&cYou do not have permission to use this command");

    public static String alreadyOnCheck = ConfigManager.getString("messages.already-on-check", "&cPlayer is already on check");

    public static String alreadyChecking = ConfigManager.getString("messages.already-checking", "&cYou are already checking player");

    public static String playerTriedToUseCommand = ConfigManager.getString("messages.player-used-command", "&fPlayer tried to use command");
    public static String cannotUseCommandsOnCheck = ConfigManager.getString("messages.no-commands-on-check", "&cYou can't use commands while on check!");

}
