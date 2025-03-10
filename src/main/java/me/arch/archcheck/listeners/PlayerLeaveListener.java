package me.arch.archcheck.listeners;

import me.arch.archcheck.ArchCheck;
import me.arch.archcheck.utils.BossBarUtil;
import me.arch.archcheck.utils.StopCheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;
import java.util.UUID;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        Player checker = getChecker(player);
        Player checkedPlayer = getCheckedPlayer(player);

        // If the player leaving is being checked, notify the checker
        if (checker != null && checker.isOnline()) {
            StopCheckUtil.stopCheck(player); // Stop check if checker left game
            BossBarUtil.removePlayerFromBossBar(player);
            checker.sendMessage("The player you were checking (" + player.getName() + ") has left the game.");
        }

        // Now check if the player leaving is a checker, notify the checked player
        if (isChecker(player) && checkedPlayer != null && checkedPlayer.isOnline())
            checkedPlayer.sendMessage("Your checker (" + player.getName() + ") has left the game.");
    }

    // Helper method to get the checker for a player being checked
    private static Player getChecker(Player player) {
        if (ArchCheck.getPlayersOnCheck().containsKey(player.getUniqueId())) {
            UUID checkerUUID = ArchCheck.getPlayersOnCheck().get(player.getUniqueId());
            return Bukkit.getPlayer(checkerUUID);
        }
        return null;
    }

    // Helper method to check if a player is a checker
    private static boolean isChecker(Player player) {
        return ArchCheck.getPlayersOnCheck().containsValue(player.getUniqueId());
    }

    // Helper method to get the player being checked by a checker
    private static Player getCheckedPlayer(Player checker) {
        return ArchCheck.getPlayersOnCheck().entrySet().stream()
                .filter(entry -> entry.getValue().equals(checker.getUniqueId()))
                .map(entry -> Bukkit.getPlayer(entry.getKey()))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

}
