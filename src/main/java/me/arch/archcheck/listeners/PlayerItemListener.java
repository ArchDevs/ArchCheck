package me.arch.archcheck.listeners;

import me.arch.archcheck.managers.CheckManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerItemListener implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        if (CheckManager.isOnCheck(e.getPlayer().getUniqueId())) { e.setCancelled(true); }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        if (!(e instanceof Player)) return;

        if (CheckManager.isOnCheck(e.getPlayer().getUniqueId())) { e.setCancelled(true); }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        if (CheckManager.isOnCheck(e.getPlayer().getUniqueId())) { e.setCancelled(true); }
    }
}
