package me.arch.archcheck.utils;

import me.arch.archcheck.utils.config.ConfigValues;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarUtil {

    // TODO Make bossbar for time check. Task for updating bossbar every second

    private static BossBar bossBar;

    private static void createBossBar() {

        bossBar = Bukkit.createBossBar(
                ChatUtil.format(ConfigValues.bossbarTitle),
                BarColor.valueOf(ConfigValues.bossbarColor.toUpperCase()),
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
