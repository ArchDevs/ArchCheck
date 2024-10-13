package me.arch.archcheck.commands;

import me.arch.archcheck.commands.subcommands.StartSubCommand;
import me.arch.archcheck.commands.subcommands.StopSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private List<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new StartSubCommand());
        subCommands.add(new StopSubCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
