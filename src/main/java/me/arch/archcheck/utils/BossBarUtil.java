package me.arch.archcheck.utils;

import me.arch.archcheck.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarUtil {

    // TODO Make bossbar for time check. Task for updating bossbar every second

    private static BossBar bossBar;

    public static void startBossBar(Player player) {
        BarColor color = BarColor.valueOf(ConfigManager.getString("bossbar.color", "RED").toUpperCase());

        bossBar = Bukkit.createBossBar(
                ConfigManager.getString("bossbar.title", "&fCheck"),
                color,
                BarStyle.SOLID);

        bossBar.addPlayer(player);
    }

    public static void stopBossBar(Player player) {
        bossBar.removePlayer(player);
    }

}
