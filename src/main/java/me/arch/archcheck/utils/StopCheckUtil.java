package me.arch.archcheck.utils;

import me.arch.archcheck.ArchCheck;
import me.arch.archcheck.ConfigManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StopCheckUtil {

    public static void stopCheck(Player player) {
        UUID playerID = player.getUniqueId();

        if (ArchCheck.getPlayersOnCheck().containsKey(playerID)) {
            // If player is on check remove him
            ArchCheck.getPlayersOnCheck().remove(playerID);

        } else {
            player.sendMessage(ChatUtil.format(ConfigManager.getString("messages.not-on-check", "&cPlayer is not on check")));
        }
    }
}
