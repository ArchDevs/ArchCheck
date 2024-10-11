package me.arch.archcheck.commands;

import me.arch.archcheck.commands.subcommands.StartSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private List<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new StartSubCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
