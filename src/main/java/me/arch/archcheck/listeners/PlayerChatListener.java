package me.arch.archcheck.listeners;

import me.arch.archcheck.ArchCheck;
import me.arch.archcheck.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
                player.sendMessage(ChatUtil.format("Checker is not available."));
                ChatUtil.executeConsole("check stop " + player.getName());
                return;
            }

            // Block commands for the player
            if (message.startsWith("/")) {
                checker.sendMessage(ChatUtil.format("Player tried to use command: " + message));
                player.sendMessage(ChatUtil.format("You can't use commands while on check."));
                e.setCancelled(true);
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
