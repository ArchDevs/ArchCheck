package me.arch.archcheck.listeners;

import me.arch.archcheck.managers.CheckManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (CheckManager.isOnCheck(e.getPlayer().getUniqueId())) { e.setCancelled(true); }
    }
}
