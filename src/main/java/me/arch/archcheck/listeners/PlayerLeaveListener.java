package me.arch.archcheck.listeners;

import me.arch.archcheck.managers.CheckManager;
import me.arch.archcheck.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        Player checker = CheckManager.getChecker(player);

        // If the player leaving is being checked, notify the checker
        if (checker != null && checker.isOnline()) {
            CheckManager.stopCheck(player.getUniqueId(), checker.getUniqueId());
            checker.sendMessage(ChatUtil.format(String.format("The player you were checking &6%s has left the game.", player.getName())));
        }

        if (player.isOnline() && player.equals(checker)) {
            CheckManager.stopCheck(player.getUniqueId(), checker.getUniqueId());
            player.sendMessage(ChatUtil.format(String.format("The checker &6%s&r left from the game.", checker.getName())));

        }
    }

}
