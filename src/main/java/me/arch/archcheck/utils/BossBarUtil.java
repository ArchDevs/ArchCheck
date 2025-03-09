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

    private static void createBossBar() {
        BarColor color = BarColor.valueOf(ConfigManager.getString("bossbar.color", "RED").toUpperCase());

        bossBar = Bukkit.createBossBar(
                ChatUtil.format(ConfigManager.getString("bossbar.title", "&fCheck")),
                color,
                BarStyle.SOLID);

    }

    public static void addPlayerToBossBar(Player player) {
        createBossBar();
        bossBar.addPlayer(player);
    }

    public static void removePlayerFromBossBar(Player player) {
        bossBar.removePlayer(player);
    }

}
