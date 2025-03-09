package me.arch.archcheck.listeners;

import me.arch.archcheck.ArchCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBlockListener implements Listener {

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent e) {
        if (!(e instanceof Player)) return;

        if (ArchCheck.getPlayersOnCheck().containsKey(e.getPlayer().getUniqueId())) { e.setCancelled(true); }
    }
}
