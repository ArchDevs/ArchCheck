package me.arch.archcheck.listeners;

import me.arch.archcheck.managers.CheckManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerItemListener implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        if (CheckManager.isOnCheck(e.getPlayer().getUniqueId())) { e.setCancelled(true); }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            if (CheckManager.isOnCheck(event.getEntity().getUniqueId())) { event.setCancelled(true); }
        }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        if (CheckManager.isOnCheck(e.getPlayer().getUniqueId())) { e.setCancelled(true); }
    }
}
