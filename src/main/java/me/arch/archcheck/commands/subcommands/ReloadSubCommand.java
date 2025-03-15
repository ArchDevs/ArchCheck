package me.arch.archcheck.commands.subcommands;

import me.arch.archcheck.ArchCheck;
import me.arch.archcheck.commands.SubCommand;
import me.arch.archcheck.utils.ChatUtil;
import me.arch.archcheck.utils.config.ConfigValues;
import org.bukkit.entity.Player;

public class ReloadSubCommand extends SubCommand {
    @Override
    public String getName() { return "reload"; }

    @Override
    public String getDescription() { return "Reloads config of plugin"; }

    @Override
    public String getSyntax() { return "/check reload"; }

    @Override
    public void perform(Player player, String[] args) {
        ArchCheck.getInstance().reloadConfig();
        player.sendMessage(ChatUtil.format(ConfigValues.configReloaded));
    }
}
