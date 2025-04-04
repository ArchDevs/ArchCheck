package me.arch.archcheck.listeners;

import me.arch.archcheck.managers.CheckManager;
import me.arch.archcheck.utils.ChatUtil;
import me.arch.archcheck.utils.config.ConfigValues;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        if (CheckManager.isOnCheck(player.getUniqueId())) {
            Player checker = CheckManager.getChecker(player);
            if (checker != null) {
                e.setCancelled(true);
                checker.sendMessage(ChatUtil.format(player.getName() + ": " + message));
            }
        }

        if (CheckManager.isPlayerChecker(player.getUniqueId())) {
            Player suspect = CheckManager.getSuspectForChecker(player);
            if (suspect != null) {
                e.setCancelled(true);
                suspect.sendMessage(ChatUtil.format(player.getName() + ": " + message));
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        // Check if the player is on check (is being checked)
        if (CheckManager.isOnCheck(player.getUniqueId())) {

            // Block the command and notify the player and checker
            if (message.startsWith("/")) {
                e.setCancelled(true);
                // Notify the checker
                CheckManager.getChecker(player).sendMessage(ChatUtil.format(ConfigValues.playerTriedToUseCommand + "&r Command was: " + message));
                // Notify the player
                player.sendMessage(ChatUtil.format(ConfigValues.cannotUseCommandsOnCheck));
            }
        }
    }
}
