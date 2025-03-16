package me.arch.archcheck.listeners;

import me.arch.archcheck.managers.CheckManager;
import me.arch.archcheck.utils.ChatUtil;
import me.arch.archcheck.utils.CheckSession;
import me.arch.archcheck.utils.config.ConfigValues;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerChatListener implements Listener {

    private static Player suspect;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        CheckSession session = CheckManager.getCheckSession(player.getUniqueId());

        if (session == null) return;

        if (player == Bukkit.getPlayer(session.getPlayerUuid())) {

            Player checker = Bukkit.getPlayer(session.getCheckerUuid());
            suspect = player;
            if (checker != null) {
                System.out.println(checker.getName());
                checker.sendMessage(ChatUtil.format(player.getName() + ": " + message));
            }
        } else if (player == Bukkit.getPlayer(session.getCheckerUuid())) {

            if (suspect != null) {
                System.out.println(suspect.getName());
                suspect.sendMessage(ChatUtil.format(player.getName() + ": " + message));
            }
        }

        e.setCancelled(true);
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
