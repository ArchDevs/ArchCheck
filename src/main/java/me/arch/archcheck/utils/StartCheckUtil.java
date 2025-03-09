package me.arch.archcheck.utils;

import me.arch.archcheck.ArchCheck;
import me.arch.archcheck.ConfigManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StartCheckUtil {

    public static void startCheck(Player player, Player checker) {
        final UUID playerID = player.getUniqueId();
        final UUID checkerID = checker.getUniqueId();

        if (!ArchCheck.getPlayersOnCheck().containsKey(playerID)) {
            // If player is not on check add him
            ArchCheck.getPlayersOnCheck().put(playerID, checkerID);
        } else {
            player.sendMessage(ChatUtil.format(ConfigManager.getString("messages.already-on-check", "&cPlayer is already on check")));
        }
    }


    // TODO Add check option on time
//    public static void startCheck(Player player, int time) {
//
//    }
}
