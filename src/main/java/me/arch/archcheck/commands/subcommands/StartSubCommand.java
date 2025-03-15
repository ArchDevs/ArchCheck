package me.arch.archcheck.commands.subcommands;

import me.arch.archcheck.managers.CheckManager;
import me.arch.archcheck.commands.SubCommand;
import me.arch.archcheck.utils.ChatUtil;
import me.arch.archcheck.utils.config.ConfigValues;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class StartSubCommand extends SubCommand {
    @Override
    public String getName() { return "start"; }

    @Override
    public String getDescription() { return "Start a player checking"; }

    @Override
    public String getSyntax() { return "/check start <player> [time in seconds]"; }

    @Override
    public void perform(Player checker, String[] args) {
        if (CheckManager.isPlayerChecker(checker.getUniqueId())) {
            checker.sendMessage(ChatUtil.format(ConfigValues.alreadyChecking));
            return;
        }

        Player player = Bukkit.getPlayerExact(args[1]);
        if (player == null) {
            checker.sendMessage(ChatUtil.format(ConfigValues.noPlayer));
            return;
        }

        World world = Bukkit.getWorld(ConfigValues.worldToTeleportPlayer);
        int xCord = ConfigValues.xCord;
        int yCord = ConfigValues.yCord;
        int zCord = ConfigValues.zCord;

        if (ConfigValues.teleportPlayerOnCheck) {
            player.teleport(new Location(world, xCord, yCord, zCord));
        }

        CheckManager.startCheck(player.getUniqueId(), checker.getUniqueId());
    }
}