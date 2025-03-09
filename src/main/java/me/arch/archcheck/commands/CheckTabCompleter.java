package me.arch.archcheck.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheckTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        switch (args.length) {
            case 1:
                return Arrays.asList("start", "stop", "reload");
            case 2:
                if (args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("stop")) {
                    return getOnlinePlayers();
                }
        }

        return Collections.emptyList();
    }

    private static List<String> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }

}
