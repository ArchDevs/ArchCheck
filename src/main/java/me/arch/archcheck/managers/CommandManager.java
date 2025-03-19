package me.arch.archcheck.managers;

import lombok.Getter;
import me.arch.archcheck.commands.SubCommand;
import me.arch.archcheck.commands.subcommands.ReloadSubCommand;
import me.arch.archcheck.commands.subcommands.StartSubCommand;
import me.arch.archcheck.commands.subcommands.StopSubCommand;
import me.arch.archcheck.utils.ChatUtil;
import me.arch.archcheck.utils.config.ConfigValues;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager implements CommandExecutor {

    private final List<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new StartSubCommand());
        subCommands.add(new StopSubCommand());
        subCommands.add(new ReloadSubCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.hasPermission("archcheck.check")) {
            player.sendMessage(ChatUtil.format(ConfigValues.noPerms));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatUtil.format(ConfigValues.noArgs));
            return true;
        }

        for (int i = 0; i < getSubCommands().size(); i++) {
            if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                getSubCommands().get(i).perform(player, args);
            }
        }

        return true;
    }
}
