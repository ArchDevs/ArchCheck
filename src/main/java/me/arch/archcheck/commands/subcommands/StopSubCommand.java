package me.arch.archcheck.commands.subcommands;

import me.arch.archcheck.managers.CheckManager;
import me.arch.archcheck.commands.SubCommand;
import me.arch.archcheck.utils.BossBarUtil;
import me.arch.archcheck.utils.ChatUtil;
import me.arch.archcheck.utils.config.ConfigValues;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StopSubCommand extends SubCommand {
    @Override
    public String getName() { return "stop"; }

    @Override
    public String getDescription() { return "Stop player checking"; }

    @Override
    public String getSyntax() { return "/check stop <player>"; }

    @Override
    public void perform(Player checker, String[] args) {
        final Player player = Bukkit.getPlayerExact(args[1]);
        if (player == null) {
            checker.sendMessage(ChatUtil.format(ConfigValues.noPlayer));
            return;
        }

        BossBarUtil.removeBossBar(player, checker);
        CheckManager.removeEffects(player);
        CheckManager.stopCheck(player.getUniqueId(), checker.getUniqueId());
    }
}
