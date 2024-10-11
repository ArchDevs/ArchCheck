package me.arch.archcheck.listeners;

import me.arch.archcheck.ArchCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerItemListener implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        final Player player = e.getPlayer();

        if (ArchCheck.getPlayersOnCheck().containsKey(player.getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        final Player player = e.getPlayer();

        if (ArchCheck.getPlayersOnCheck().containsKey(player.getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        final Player player = e.getPlayer();

        if (ArchCheck.getPlayersOnCheck().containsKey(player.getUniqueId())) {
            e.setCancelled(true);
        }
    }
}
