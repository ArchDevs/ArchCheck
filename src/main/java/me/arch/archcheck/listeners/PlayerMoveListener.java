package me.arch.archcheck.listeners;

import me.arch.archcheck.ArchCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        final Player player = e.getPlayer();

        if (ArchCheck.getPlayersOnCheck().containsKey(player.getUniqueId())) {
            e.setCancelled(true);
        }
    }
}
