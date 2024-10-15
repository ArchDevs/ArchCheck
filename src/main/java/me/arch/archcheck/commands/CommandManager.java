package me.arch.archcheck.commands;

import lombok.Getter;
import me.arch.archcheck.ConfigManager;
import me.arch.archcheck.commands.subcommands.ReloadSubCommand;
import me.arch.archcheck.commands.subcommands.StartSubCommand;
import me.arch.archcheck.commands.subcommands.StopSubCommand;
import me.arch.archcheck.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {

    @Getter
    private List<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new StartSubCommand());
        subCommands.add(new StopSubCommand());
        subCommands.add(new ReloadSubCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatUtil.format(ConfigManager.getString("no-arguments", "&cPlease provide some arguments to command")));
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
