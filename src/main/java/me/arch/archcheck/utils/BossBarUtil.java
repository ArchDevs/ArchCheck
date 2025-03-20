package me.arch.archcheck.utils;

import me.arch.archcheck.utils.config.ConfigValues;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarUtil {

    private static BossBar bossBar;

    public static void createBossBar(Player player, Player checker) {
        bossBar = Bukkit.createBossBar(
                ChatUtil.format(ConfigValues.bossbarTitle),
                BarColor.valueOf(ConfigValues.bossbarColor.toUpperCase()),
                BarStyle.SOLID);

        bossBar.addPlayer(player);
        bossBar.addPlayer(checker);
    }

    public static void removeBossBar(Player player, Player checker) {
        if (bossBar != null) {
            bossBar.removePlayer(player);
            bossBar.removePlayer(checker);
        }
    }

}
