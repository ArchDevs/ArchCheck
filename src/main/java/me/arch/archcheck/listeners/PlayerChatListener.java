package me.arch.archcheck.listeners;

import me.arch.archcheck.ArchCheck;
import me.arch.archcheck.ConfigManager;
import me.arch.archcheck.utils.BossBarUtil;
import me.arch.archcheck.utils.ChatUtil;
import me.arch.archcheck.utils.StopCheckUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.UUID;

public class PlayerChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();
        String message = e.getMessage();

        // Check if the player is on check (is being checked)
        if (ArchCheck.getPlayersOnCheck().containsKey(playerUUID)) {
            Player checker = player.getServer().getPlayer(ArchCheck.getPlayersOnCheck().get(playerUUID));

            if (checker == null) {
                player.sendMessage(ChatUtil.format(ConfigManager.getString("messages.checker-not-found", "&cChecker is not online")));
                StopCheckUtil.stopCheck(player); // Stop check if checker left game
                BossBarUtil.removePlayerFromBossBar(player);
                return;
            }

            // Forward the player's message to the checker
            e.setCancelled(true);
            checker.sendMessage(ChatUtil.format(player.getName() + ": " + message));
            return;
        }

        // Check if the player is a checker (is checking someone)
        if (ArchCheck.getPlayersOnCheck().containsValue(playerUUID)) {
            UUID checkedPlayerUUID = getCheckedPlayer(playerUUID);
            Player checkedPlayer = player.getServer().getPlayer(checkedPlayerUUID);

            if (checkedPlayer != null && !message.startsWith("/")) {
                e.setCancelled(true);
                checkedPlayer.sendMessage(ChatUtil.format(player.getName() + ": " + message));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();
        String message = e.getMessage();

        // Check if the player is on check (is being checked)
        if (ArchCheck.getPlayersOnCheck().containsKey(playerUUID)) {
            Player checker = player.getServer().getPlayer(ArchCheck.getPlayersOnCheck().get(playerUUID));

            if (checker == null) {
                player.sendMessage(ChatUtil.format(ConfigManager.getString("messages.checker-not-found", "&cChecker is not online")));
                StopCheckUtil.stopCheck(player); // Stop check if checker left game
                BossBarUtil.removePlayerFromBossBar(player);
                return;
            }

            // Block the command and notify the player and checker
            if (message.startsWith("/")) {
                e.setCancelled(true);

                // Notify the checker
                checker.sendMessage(ChatUtil.format(ConfigManager.getString("player-used-command", "&fPlayer tried to use command") + ChatColor.RESET + " " + message));

                // Notify the player
                player.sendMessage(ChatUtil.format(ConfigManager.getString("no-command-on-check", "&cYou can't use commands while on check!")));
            }
        }
    }

    // Helper method to get the player being checked by a checker
    private UUID getCheckedPlayer(UUID checkerUUID) {
        for (UUID playerUUID : ArchCheck.getPlayersOnCheck().keySet()) {
            if (ArchCheck.getPlayersOnCheck().get(playerUUID).equals(checkerUUID)) {
                return playerUUID;
            }
        }
        return null;
    }
}
